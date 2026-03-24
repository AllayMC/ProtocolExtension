package org.allaymc.protocol.extension.packet;

import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;

@Getter
@Setter
public class NetEasePlayerAuthInputPacket extends PlayerAuthInputPacket {
    private boolean cameraDeparted;
    private boolean thirdPersonPerspective;
    private Vector2f playerRotationToCamera = Vector2f.from(0, 0);
    private boolean readyPosDeltaDirty;
    private boolean onGround;
    private int resetPosition;
}
