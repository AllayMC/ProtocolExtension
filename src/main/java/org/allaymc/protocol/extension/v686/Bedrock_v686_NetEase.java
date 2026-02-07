package org.allaymc.protocol.extension.v686;

import org.allaymc.protocol.extension.common.ConfirmSkinSerializer;
import org.allaymc.protocol.extension.common.NetEaseJsonSerializer;
import org.allaymc.protocol.extension.common.PyRpcSerializer;
import org.allaymc.protocol.extension.common.StoreBuySuccessSerializer;
import org.allaymc.protocol.extension.packet.ConfirmSkinPacket;
import org.allaymc.protocol.extension.packet.NetEaseJsonPacket;
import org.allaymc.protocol.extension.packet.PyRpcPacket;
import org.allaymc.protocol.extension.packet.StoreBuySuccessPacket;
import org.allaymc.protocol.extension.v630.BedrockCodecHelper_v630_NetEase;
import org.allaymc.protocol.extension.v630.PlayerAuthInputSerializer_v630_NetEase;
import org.allaymc.protocol.extension.v766.PlayerEnchantOptionsSerializer_v766_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630;
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * NetEase variant of the v686 codec.
 */
public class Bedrock_v686_NetEase extends Bedrock_v686 {

    protected static final TypeMap<ContainerSlotType> CONTAINER_SLOT_TYPES = Bedrock_v630.CONTAINER_SLOT_TYPES
            .toBuilder()
            // NetEase: RECIPE_CUSTOM
            .shift(17, 1)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v686.CODEC.toBuilder()
            .raknetProtocolVersion(8)
            .helper(() -> new BedrockCodecHelper_v630_NetEase(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v630_NetEase.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v686_NetEase.INSTANCE)
            .updateSerializer(PlayerEnchantOptionsPacket.class, PlayerEnchantOptionsSerializer_v766_NetEase.INSTANCE)
            .registerPacket(PyRpcPacket::new, PyRpcSerializer.INSTANCE, 200, PacketRecipient.BOTH)
            .registerPacket(StoreBuySuccessPacket::new, StoreBuySuccessSerializer.INSTANCE, 202, PacketRecipient.BOTH) // TODO: check packet recipient
            .registerPacket(NetEaseJsonPacket::new, NetEaseJsonSerializer.INSTANCE, 203, PacketRecipient.BOTH)
            .registerPacket(ConfirmSkinPacket::new, ConfirmSkinSerializer.INSTANCE, 228, PacketRecipient.CLIENT)
            .build();
}
