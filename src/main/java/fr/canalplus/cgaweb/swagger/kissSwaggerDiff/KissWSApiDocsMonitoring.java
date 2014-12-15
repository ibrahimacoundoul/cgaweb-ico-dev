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

public class KissWSApiDocsMonitoring {

	private final static String KISS_WS_API_DOCS_URL = "http://frcp03vdv0184.cpdev.local:8083/cgawebKissWS/rest/api-docs";
	private final static String JSON_FILES_DIR_PATH = "src/main/resources";

	private final static String UTF_8_FORMAT = "UTF-8";
	private final static String JSON_FILES_EXTENTION = ".json";

	public static void manageKissSwaggerApisDiffrences() throws Exception {
		URL url = new URL(KISS_WS_API_DOCS_URL);
		StringBuffer output = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8_FORMAT));
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
		String path = KISS_WS_API_DOCS_URL + wsPathName;
		String response = callUrl(path);
		manageWSResponseFile(wsPathName, response);
		System.out.println("[" + wsPathName + "]" + "\n");
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8_FORMAT));
			for (String line; (line = reader.readLine()) != null;) {
				output.append(line);
			}
		} catch (Exception e) {
			throw e;
		}
		return output.toString();
	}

	/**
	 * Create or update json file for webservice response
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
		fileName.append(JSON_FILES_EXTENTION);

		PrintWriter writer = new PrintWriter(fileName.toString(), UTF_8_FORMAT);
		writer.println(indented);
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		manageKissSwaggerApisDiffrences();
	}
}