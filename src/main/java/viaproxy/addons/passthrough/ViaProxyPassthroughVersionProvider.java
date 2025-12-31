package viaproxy.addons.passthrough;

import com.viaversion.viaaprilfools.api.AprilFoolsProtocolVersion;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.raphimc.viaproxy.protocoltranslator.providers.ViaProxyVersionProvider;

public class ViaProxyPassthroughVersionProvider extends ViaProxyVersionProvider {

    private static int passthroughMaxProtocol;

    private static boolean passthroughSnapshots;

    public ViaProxyPassthroughVersionProvider() {
        passthroughMaxProtocol = PassthroughConfig.getPassthroughMaxProtocol();
        passthroughSnapshots = PassthroughConfig.shouldPassthroughSnapshots();
    }

    @Override
    public ProtocolVersion getClosestServerProtocol(UserConnection connection) {
        if (passthroughMaxProtocol > 0 ) {
            ProtocolVersion clientProtocol = connection.getProtocolInfo().protocolVersion();
            if (shouldPassthrough(clientProtocol)) {
                return clientProtocol;
            }
        }
        return super.getClosestServerProtocol(connection);
    }

    public boolean shouldPassthrough(ProtocolVersion clientProtocol) {
        if (AprilFoolsProtocolVersion.PROTOCOLS.contains(clientProtocol)) {
            return passthroughSnapshots;
        } else {
            // Return the same version as the client; triggers passthrough
            return (clientProtocol.getVersion() <= passthroughMaxProtocol);
        }
    }
}
