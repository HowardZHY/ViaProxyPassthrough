/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package viaproxy.addons.passthrough;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import net.lenni0451.lambdaevents.EventHandler;
import net.raphimc.viaproxy.ViaProxy;
import net.raphimc.viaproxy.plugins.ViaProxyPlugin;
import net.raphimc.viaproxy.plugins.events.ViaLoadingEvent;

public class ViaProxyPassthrough extends ViaProxyPlugin {

    @Override
    public void onEnable() {
        ViaProxy.EVENT_MANAGER.register(this);
        PassthroughConfig.load();
    }

    @EventHandler
    public void onViaLoading(final ViaLoadingEvent event) {
        Via.getManager().getProviders().use(VersionProvider.class, new ViaProxyPassthroughVersionProvider());
    }
}
