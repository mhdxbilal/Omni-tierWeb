package com.example.network

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor

class VpnRoutingManager : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setupVpn()
        return START_STICKY
    }

    private fun setupVpn() {
        if (vpnInterface != null) return

        val builder = Builder()
        builder.addAddress("10.0.0.2", 32)
        builder.addRoute("0.0.0.0", 0)
        builder.addDnsServer("8.8.8.8")
        
        // Force network streams strictly through localized WireGuard hooks or isolated loopbacks
        builder.setSession("PrivacyShieldVPN")
        // builder.setConfigureIntent(null) // No configure intent needed

        vpnInterface = builder.establish()

        // Pass this descriptor to the Rust networking interceptor
        // val fd = vpnInterface?.fd
        // NativeBridge.startNetworkShielder(fd)
    }

    override fun onDestroy() {
        super.onDestroy()
        vpnInterface?.close()
        vpnInterface = null
    }
}
