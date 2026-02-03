package org.allaymc.protocol.extension.v766;

import io.netty.buffer.ByteBuf;
import org.allaymc.protocol.extension.packet.StoreBuySuccessPacket;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;

/**
 * @author daoge_cmd
 */
public class StoreBuySuccessSerializer_v766_NetEase implements BedrockPacketSerializer<StoreBuySuccessPacket> {
    public static final StoreBuySuccessSerializer_v766_NetEase INSTANCE = new StoreBuySuccessSerializer_v766_NetEase();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StoreBuySuccessPacket packet) {
        // noop
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StoreBuySuccessPacket packet) {
        // noop
    }
}
