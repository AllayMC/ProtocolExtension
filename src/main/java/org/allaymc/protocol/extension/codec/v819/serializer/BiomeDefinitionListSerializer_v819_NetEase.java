package org.allaymc.protocol.extension.codec.v819.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.BiomeDefinitionListSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionChunkGenData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.index.IndexedList;

import java.awt.*;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class BiomeDefinitionListSerializer_v819_NetEase extends BiomeDefinitionListSerializer_v800 {

    public static final BiomeDefinitionListSerializer_v819_NetEase INSTANCE = new BiomeDefinitionListSerializer_v819_NetEase();

    @Override
    protected BiomeDefinitionData readDefinition(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        Integer id = this.readDefinitionId(buffer, helper);
        float temperature = buffer.readFloatLE();
        float downfall = buffer.readFloatLE();
        float redSporeDensity = buffer.readFloatLE();
        float blueSporeDensity = buffer.readFloatLE();
        float ashDensity = buffer.readFloatLE();
        float whiteAshDensity = buffer.readFloatLE();
        float depth = buffer.readFloatLE();
        float scale = buffer.readFloatLE();
        Color mapWaterColor = new Color(buffer.readIntLE(), true);
        boolean rain = buffer.readBoolean();

        // NetEase only
        buffer.readIntLE(); // mDimensionId
        helper.readString(buffer); // mWorldName

        IndexedList<String> tags = helper.readOptional(buffer, null, byteBuf -> {
            int length = VarInts.readUnsignedInt(byteBuf);
            Preconditions.checkArgument(byteBuf.isReadable(length * 2), "Not enough readable bytes for tags");
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = byteBuf.readUnsignedShortLE();
            }
            return new IndexedList<>(strings, array);
        });

        BiomeDefinitionChunkGenData chunkGenData = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readDefinitionChunkGen(buf, aHelper, strings));

        return new BiomeDefinitionData(id, temperature, downfall, redSporeDensity, blueSporeDensity,
                ashDensity, whiteAshDensity, depth, scale, mapWaterColor,
                rain, tags, chunkGenData);
    }

    @Override
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition, SequencedHashSet<String> strings) {
        this.writeDefinitionId(buffer, helper, definition);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getRedSporeDensity());
        buffer.writeFloatLE(definition.getBlueSporeDensity());
        buffer.writeFloatLE(definition.getAshDensity());
        buffer.writeFloatLE(definition.getWhiteAshDensity());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColor().getRGB());
        buffer.writeBoolean(definition.isRain());

        // NetEase only
        buffer.writeIntLE(0); // mDimensionId
        helper.writeString(buffer, ""); // mWorldName

        helper.writeOptionalNull(buffer, definition.getTags(), (byteBuf, aHelper, tags) -> {
            VarInts.writeUnsignedInt(byteBuf, tags.size());
            for (String tag : tags) {
                byteBuf.writeShortLE(strings.addAndGetIndex(tag));
            }
        });
        helper.writeOptionalNull(buffer, definition.getChunkGenData(),
                (buf, aHelper, data) -> writeDefinitionChunkGen(buf, aHelper, data, strings));
    }
}
