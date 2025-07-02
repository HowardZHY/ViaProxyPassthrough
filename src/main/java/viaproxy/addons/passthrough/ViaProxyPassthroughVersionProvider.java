package viaproxy.addons.passthrough;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.raphimc.viaproxy.protocoltranslator.providers.ViaProxyVersionProvider;

public class ViaProxyPassthroughVersionProvider extends ViaProxyVersionProvider {

    private static int passthroughMaxProtocol;

    public ViaProxyPassthroughVersionProvider() {
        passthroughMaxProtocol = PassthroughConfig.getPassthroughMaxProtocol();
    }

    @Override
    public ProtocolVersion getClosestServerProtocol(UserConnection connection) {
        if (passthroughMaxProtocol > 0 ) {
            ProtocolVersion clientProtocol = connection.getProtocolInfo().protocolVersion();
            if (clientProtocol.getVersion() <= passthroughMaxProtocol) {
                // Return the same version as the client; triggers passthrough
                return clientProtocol;
            }
        }
        return super.getClosestServerProtocol(connection);
    }
}
