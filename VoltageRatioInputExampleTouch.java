import com.phidget22.*;

public class VoltageRatioInputExampleTouch {

   public static void main(String[] args) throws Exception {
        //Enable logging to stdout
      com.phidget22.Log.enable(LogLevel.INFO, null);
        
      VoltageRatioInput ch0 = new VoltageRatioInput();
      ch0.setChannel(0);
      
      VoltageRatioInput ch1 = new VoltageRatioInput();
       ch1.setChannel(1);  
      ch0.addAttachListener(
         new AttachListener() {
            public void onAttach(AttachEvent ae) {
               VoltageRatioInput phid = (VoltageRatioInput) ae.getSource();
               try {
                  if(phid.getDeviceClass() != DeviceClass.VINT){
                     System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
                     phid.setVoltageRatioChangeTrigger(0.5);
                  }
                  else{
                     System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
                  }
               } 
               catch (PhidgetException ex) {
                  System.out.println(ex.getDescription());
               }
            }
         });
   
      ch1.addAttachListener(
         new AttachListener() {
            public void onAttach(AttachEvent ae) {
               VoltageRatioInput phid = (VoltageRatioInput) ae.getSource();
               try {
                  if(phid.getDeviceClass() != DeviceClass.VINT){
                     System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
                     phid.setVoltageRatioChangeTrigger(0.5);
                  }
                  else{
                     System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
                  }
               } 
               catch (PhidgetException ex) {
                  System.out.println(ex.getDescription());
               }
            }
         });
      ch0.addVoltageRatioChangeListener(
         new VoltageRatioInputVoltageRatioChangeListener() {
            public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
               System.out.printf("Voltage Ratio Changed at channel 0: %.3g\n", e.getVoltageRatio());
            }
         });
      ch1.addVoltageRatioChangeListener(
         new VoltageRatioInputVoltageRatioChangeListener() {
            public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
               System.out.printf("Voltage Ratio Changed at channel 1: %.3g\n", e.getVoltageRatio());
            }
         });
      try {
      
         System.out.println("Opening and waiting 5 seconds for attachment...");
         ch0.open(2000);
      
         ch1.open(2000);           
                            
         System.out.println("\n\nGathering data for 20 seconds\n\n");
         Thread.sleep(20000);
      
      
         ch0.close();
         ch1.close();
         System.out.println("\nClosed Voltage Ratio Input");
            
      } 
      catch (PhidgetException ex) {
         System.out.println(ex.getDescription());
      }
   }
}
