package fr.canalplus.cgaweb.swagger.kissSwaggerDiff;

/**
 * @author icoundoul
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class KissSwaggerMainHandler {

	public final static String SWAGGER_API_DOCS_BASE_PATH = "http://frcp03vdv0184.cpdev.local:8083/cgawebKissWS/rest/api-docs";
	public final static String JSON_FILES_DIR_PATH = "src/main/resources";

	public final static String UTF8_FORMAT = "UTF-8";
	public final static String JSON_EXTENTION = ".json";

	public static void manageKissSwaggerApisDiffrences() throws Exception {
		URL url = new URL(SWAGGER_API_DOCS_BASE_PATH);
		StringBuffer output = new StringBuffer();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), UTF8_FORMAT));
			for (String line; (line = reader.readLine()) != null;) {
				output.append(line);
			}
		} catch (Exception e) {
			throw e;
		}

		manegeWSApis(output.toString());
	}

	private static void manegeWSApis(String jSonData) throws Exception {

		final JSONObject obj = new JSONObject(jSonData);
		final JSONArray apis = obj.getJSONArray("apis");
		final int n = apis.length();
		for (int i = 0; i < n; ++i) {
			final JSONObject api = apis.getJSONObject(i);
			String path = api.getString("path");
			callAndHandleWSResponse(path);
		}
	}

	private static void callAndHandleWSResponse(String wsPathName) throws Exception {
		String path = SWAGGER_API_DOCS_BASE_PATH + wsPathName;
		String response = callUrl(path);
		//update WS json file
		manageWSResponseFile(wsPathName, response);
		System.out.println("[" + wsPathName + "] : Response =>" + response + "\n");
	}

	/**
	 * Appel curl sur n'importe quelle URL et renvoie le réponse
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private static String callUrl(String path) throws Exception {
		URL url = new URL(path);
		StringBuffer output = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), UTF8_FORMAT));
			for (String line; (line = reader.readLine()) != null;) {
				output.append(line);
			}
		} catch (Exception e) {
			throw e;
		}
		return output.toString();
	}

	/**
	 * Create a file il not existe et update it contents
	 * @param wsPathName
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private static void manageWSResponseFile(String wsPathName, String response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(response, Object.class);
		String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);

		StringBuffer fileName = new StringBuffer();
		fileName.append(JSON_FILES_DIR_PATH);
		fileName.append("/");
		fileName.append(wsPathName.substring(9, wsPathName.length()));
		fileName.append(JSON_EXTENTION);

		PrintWriter writer = new PrintWriter(fileName.toString(), UTF8_FORMAT);
		writer.println(indented);
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		manageKissSwaggerApisDiffrences();
	}
}