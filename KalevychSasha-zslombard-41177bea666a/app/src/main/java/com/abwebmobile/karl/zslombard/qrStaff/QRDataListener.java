

package com.abwebmobile.karl.zslombard.qrStaff;

/**
 * The interface Qr data listener.
 */
public interface QRDataListener {

  /**
   * On detected.
   *
   * @param data
   *     the data
   */
  // Called from not main thread. Be careful
  void onDetected(final String data);
}
