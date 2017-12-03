import com.phidget22.*;

public class LCDExample {

    public static final void main(String args[]) throws Exception {
        //Enable logging to stdout
        com.phidget22.Log.enable(LogLevel.INFO, null);
        
        LCD ch = new LCD();

        ch.addAttachListener(new AttachListener() {
			public void onAttach(AttachEvent ae) {
				LCD phid = (LCD) ae.getSource();
				try {
					if(phid.getDeviceClass() != DeviceClass.VINT){
						System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
					}
					else{
						System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
					}
					if(ae.getSource().getDeviceID() == DeviceID.PN_1204){
						System.out.println("Setting arbitrary screen size");
						phid.setScreenSize(LCDScreenSize.DIMENSIONS_1X8);
					}
				} catch (PhidgetException ex) {
					System.out.println(ex.getDescription());
				}
			}
        });

          try {
            System.out.println("Opening and waiting 5 seconds for attachment...");
            ch.open(5000);
            ch.setBacklight(0.9);
            System.out.println("Writing 'Phidgets' for 10 seconds");
            ch.writeText(LCDFont.DIMENSIONS_5X8, 0, 0, "Phidgets");
            ch.flush();
            Thread.sleep(10000);
            ch.setBacklight(0);
            ch.close();
            System.out.println("\nClosed LCD");
        } catch (PhidgetException ex) {
            System.out.println(ex.getDescription());
        }
    }
}
