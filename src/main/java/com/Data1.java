package com;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Data1 implements Serializable {
    private Long id;
    private String channel;
    private String streamUrl;
    private String podConversationId;
    private String podVersion;
    private String podName;
    private Date chatStartDate;
    private Date chatLastUpdated;
    private String genesysCallBackURL;
    private String customEventCallBackURL;
    private Map<String, Data2> context;
    private Set<Data2> podCallBackURL;
    private String watermark;
    private String genesysConversationName;
    private String genesysCallBackToken;
    private String customEventCallBackToken;
    private String fromCustomerName;
    private Map<String, Data2> metadata;
    private String aToken;
    private String podSendMessageUrl;
    private Boolean messageSent;
    private Date conversationExpiresIn;
    private int conversationExpiration;
    private String podSecret;
    private Date podReconnectDateTime;
    private Date mspodCallBackDateTime;
    private List<String> someData;
    private String bToken;
    private Date expireDateTime;
    private Integer sequence;
    private Integer userMessageCount;
	private String workspaceId;
	private String encryptedpodConfigToken;
	private String activepodName;
	private Map<String, String> traceData;
	private String head1;
}
