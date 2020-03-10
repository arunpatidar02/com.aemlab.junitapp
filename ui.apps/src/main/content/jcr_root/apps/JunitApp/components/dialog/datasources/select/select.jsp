<%@page session="false"
	import="java.io.BufferedReader,
            java.io.IOException,
            java.io.InputStream,
            java.io.InputStreamReader,
            java.util.ArrayList,
            java.util.HashMap,
            java.util.List,
            javax.jcr.Node,
			javax.jcr.RepositoryException,
			java.io.IOException,
            org.apache.sling.api.resource.Resource,
            org.apache.sling.api.resource.ResourceMetadata,
            org.apache.sling.api.resource.ResourceResolver,
            org.apache.sling.api.resource.ResourceUtil,
			org.apache.sling.api.wrappers.ValueMapDecorator,
            org.apache.sling.api.resource.ValueMap,
            org.apache.sling.commons.json.JSONArray,
            org.apache.sling.commons.json.JSONObject,
            org.slf4j.Logger,
            org.slf4j.LoggerFactory,
            com.adobe.granite.ui.components.ds.DataSource,
            com.adobe.granite.ui.components.ds.EmptyDataSource,
            com.adobe.granite.ui.components.ds.SimpleDataSource,
            com.adobe.granite.ui.components.ds.ValueMapResource"%>
<%
	
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%>
<%
	
%><cq:defineObjects />
<%
	Logger logger = LoggerFactory.getLogger(this.getClass());

	ResourceResolver resolver = resource.getResourceResolver();
	// set fallback
	request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());
	
	try {
	Resource datasource = resource.getChild("datasource");
	ValueMap dsProperties = ResourceUtil.getValueMap(datasource);
	String genericListPath = dsProperties.get("options", String.class);

	if (genericListPath != null) {
		Node cfNode = resource.getResourceResolver().getResource(genericListPath + "/jcr:content")
				.adaptTo(Node.class);
		if (cfNode != null) {
			InputStream in = cfNode.getProperty("jcr:data").getBinary().getStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder strOut = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				strOut.append(line);
			}
			reader.close();

			JSONArray jsonArray = new JSONArray(strOut.toString());
			ValueMap vm = null;
			List<Resource> optionResourceList = new ArrayList<Resource>();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				String Text = "";
				String Value = "";
				vm = new ValueMapDecorator(new HashMap<String, Object>());
				Text = json.getString("text");
				Value = json.getString("value");

				vm.put("value", Value);
				vm.put("text", Text);

				if (!json.isNull("disabled") && json.getString("disabled").equals("true"))
					vm.put("disabled", true);

				if (!json.isNull("selected") && json.getString("selected").equals("true"))
					vm.put("selected", true);

				optionResourceList
						.add(new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm));

			}
			DataSource ds = new SimpleDataSource(optionResourceList.iterator());
			request.setAttribute(DataSource.class.getName(), ds);

		} else {
			logger.warn("JSON file {} is not found", genericListPath);
		}
	} else {
		logger.warn("options property is missing");
	}
	}catch (Exception e) {
		logger.error("Error in json options "+e.getMessage());
	}
%>