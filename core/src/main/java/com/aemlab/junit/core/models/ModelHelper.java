package com.aemlab.junit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.policies.ContentPolicy;
import com.day.cq.wcm.api.policies.ContentPolicyManager;

public class ModelHelper {

	private static final Logger log = LoggerFactory.getLogger(ModelHelper.class);

	private ModelHelper() {
		throw new IllegalStateException("ModelHelper class object's can't be instantiated");
	}

	/**
	 * @param resource
	 * @param modelClass class of the model
	 * @param <T>        type of class of the model
	 * @return List of models retrieved from the children of the input resource
	 */
	public static <T> List<T> getChildrenModels(final Resource resource, Class<T> modelClass) {
		List<T> items = null;
		if (resource != null) {
			items = new ArrayList<T>();

			final Iterator<Resource> childrenIterator = resource.listChildren();
			while (childrenIterator.hasNext()) {
				final Resource next = childrenIterator.next();
				final T item = next.adaptTo(modelClass);
				items.add(item);
			}
		}
		return items;
	}

	/**
	 * @param <T>
	 * @param strings array object
	 * @return List of object values
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> covertArrayToList(Object[] prop) {
		List<T> list = new ArrayList<T>();
		if (prop != null)
			list = (List<T>) Arrays.asList(prop);
		return list;
	}

	/**
	 * @param string url
	 * @return string Formatted url
	 */
	public static String getLink(String url) {
		String link = url;
		if (url != null) {
			final String regex = "(http:\\/\\/|https:\\/\\/(www)?)([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";
			final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			final Matcher matcher = pattern.matcher(url);
			if (!(matcher.find() || url.startsWith("#") || url.equals("/") || url.toLowerCase().matches(
					".*\\.(doc|docx|htm|html|odt|pdf|xls|xlsx|ods|ppt|pptx|txt|png|jpeg|jpg|xls|xlsx|svg|zip|shtml|json|log)$")
					|| url.toLowerCase().startsWith("tel:") || url.toLowerCase().startsWith("mailto:"))) {
				link = link + ".html";
			}
		}
		return link;
	}

	/**
	 * Method reads design dialog property and sets it.
	 *
	 * @param resource     - resource
	 * @param propertyName - propertyName
	 * @return <b>String</b> - property value or <b>null</b>
	 */
	public static String getDesignPropertyValue(Resource resource, String propertyName, String defaultValue) {
		ResourceResolver resourceResolver = resource.getResourceResolver();
		ContentPolicyManager policyManager = resourceResolver.adaptTo(ContentPolicyManager.class);
		if (policyManager != null) {
			ContentPolicy contentPolicy = policyManager.getPolicy(resource);
			if (contentPolicy != null) {
				return contentPolicy.getProperties().get(propertyName, defaultValue);
			}
		}
		return defaultValue;
	}

}
