package org.allaymc.protocol.extension.packet;

import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * @author daoge_cmd
 */
public class NetEasePacketType {
    public static final BedrockPacketType CONFIRM_SKIN = new BedrockPacketType("CONFIRM_SKIN");
    public static final BedrockPacketType NET_EASE_JSON = new BedrockPacketType("NET_EASE_JSON");
    public static final BedrockPacketType PY_RPC = new BedrockPacketType("PY_RPC");
    public static final BedrockPacketType STORE_BUY_SUCCESS = new BedrockPacketType("STORE_BUY_SUCCESS");
}
