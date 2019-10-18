package ru.unisuite.contentservlet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RequestParameters {

	private Integer contentDisposition;
	private Integer contentType;

	private Integer webMetaId;
	private String webMetaAlias;
	private Integer fileVersionId;
	private Integer clientId;
	private Integer entryIdInPhotoalbum;
	private Integer width;
	private Integer height;
	private boolean noCache;
	private int quality;

	public static final String webMetaIdParamName = "webMetaId";
	public static final String webMetaAliasParamName = "webMetaAlias";
	public static final String fileVersionIdParamName = "fileVersionId";
	public static final String clientIdParamName = "clientId";
	public static final String entryIdInPhotoalbumParamName = "entryIdInPhotoalbum";
	public static final String widthParamName = "width";
	public static final String heightParamName = "height";
	public static final String noCacheParamName = "noCache";
	public static final String qualityParamName = "quality";
	
	static final int DEFAULT_QUALITY = 100;
	
	private Logger logger = LoggerFactory.getLogger(RequestParameters.class.getName());
	
	public RequestParameters(Map<String, String[]> parametersMap) throws NumberFormatException {

		contentDisposition = getIntValue(parametersMap, ServletParamName.contentDisposition);
		contentType = getIntValue(parametersMap, ServletParamName.contentType);

		webMetaId = getIntValue(parametersMap, ServletParamName.webMetaId);
		webMetaAlias = getStringValue(parametersMap, ServletParamName.webMetaAlias);
		fileVersionId = getIntValue(parametersMap, ServletParamName.fileVersionId);
		clientId = getIntValue(parametersMap, ServletParamName.clientId);
		entryIdInPhotoalbum = getIntValue(parametersMap, ServletParamName.entryIdInPhotoalbum);
		width = getIntValue(parametersMap, ServletParamName.width);
		height = getIntValue(parametersMap, ServletParamName.height);

		noCache = parametersMap.containsKey(ServletParamName.cacheControl);
		
		Integer inQuality = getIntValue(parametersMap, ServletParamName.quality);
		if (inQuality != null && inQuality >=0 && inQuality <= 100) {
			quality = inQuality;
			logger.warn("Quality value is not correct. It was set by default value: " + DEFAULT_QUALITY);
		} else {
			quality = DEFAULT_QUALITY;
		}

	}

	public Integer getContentDisposition() {
		return contentDisposition;
	}

	public Integer getContentType() {
		return contentType;
	}

	public boolean getCacheControl() {
		return noCache;
	}
	
	public String getWebMetaAlias() {
		return webMetaAlias;
	}

	public Integer getWebMetaId() {
		return webMetaId;
	}

	public Integer getFileVersionId() {
		return fileVersionId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public Integer getEntryIdInPhotoalbum() {
		return entryIdInPhotoalbum;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}
	
	public int getQuality() {
		return quality;
	}

	public String getStringValue(final Map<String, String[]> parametersMap, final String parameterName) {

		String value = null;
		if (parametersMap.containsKey(parameterName)) {
			value = parametersMap.get(parameterName)[0];
		}

		return value;
	}

	public Integer getIntValue(final Map<String, String[]> parametersMap, final String parameterName)
			throws NumberFormatException {

		Integer value = null;
		if (parametersMap.containsKey(parameterName)) {

			value = Integer.parseInt(parametersMap.get(parameterName)[0]);

		}

		return value;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		String stringFormat = "%s: %s ";
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, webMetaIdParamName, webMetaId));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, webMetaAliasParamName, webMetaAlias));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, fileVersionIdParamName, fileVersionId));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, clientIdParamName, clientId));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, entryIdInPhotoalbumParamName, entryIdInPhotoalbum));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, widthParamName, width));
		
		if (webMetaId != null)
			builder.append(String.format(stringFormat, heightParamName, noCache));
		
		builder.append(String.format(stringFormat, noCacheParamName, noCache));
		
		builder.append(String.format(stringFormat, qualityParamName, quality));
		
		return builder.toString();
		
	}

}
