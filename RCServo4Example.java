import com.phidget22.*;

public class RCServo4Example {

   public static void main(String[] args) throws Exception {
        //Enable logging to stdout
      com.phidget22.Log.enable(LogLevel.INFO, null);
      RCServo[] ch=new RCServo[4];
        
      for(int i=0; i<4;i++)
      {
         ch[i] = new RCServo();
         ch[i].setChannel(i);
      }
   
      ch[0].addAttachListener(
         new AttachListener() {
            public void onAttach(AttachEvent ae) {
               RCServo phid = (RCServo) ae.getSource();
               try {
                  if(phid.getDeviceClass() != DeviceClass.VINT){
                     System.out.println("channel " + phid.getChannel() + " on device " 
                        + phid.getDeviceSerialNumber() + " attached");
                  }
                  else{
                     System.out.println("channel " + phid.getChannel() + " on device "
                        + phid.getDeviceSerialNumber() + " hub port " 
                        + phid.getHubPort() + " attached");
                  }
               } 
               catch (PhidgetException ex) {
                  System.out.println(ex.getDescription());
               }
            }
         });
   
      ch[0].addDetachListener(
         new DetachListener() {
            public void onDetach(DetachEvent de) {
               RCServo phid = (RCServo) de.getSource();
               try {
                  if (phid.getDeviceClass() != DeviceClass.VINT) {
                     System.out.println("channel " + phid.getChannel() + " on device " 
                        + phid.getDeviceSerialNumber() + " detached");
                  } 
                  else {
                     System.out.println("channel " + phid.getChannel() + " on device " 
                        + phid.getDeviceSerialNumber() + " hub port " 
                        + phid.getHubPort() + " detached");
                  }
               } 
               catch (PhidgetException ex) {
                  System.out.println(ex.getDescription());
               }
            }
         });
        
      ch[0].addTargetPositionReachedListener(
         new RCServoTargetPositionReachedListener() {
            public void onTargetPositionReached(RCServoTargetPositionReachedEvent e) {
               System.out.printf("Target Position Reached: %.3g\n", e.getPosition());
            }
         });
        
      try {
         System.out.println("Opening and waiting 5 seconds for attachment...");
         for( int i=0;i<4;i++)
            ch[i].open(5000);
            
            
         System.out.println("Setting target position to 90");
         for( int i=0;i<4;i++)
            ch[i].setTargetPosition(90.0);
      
         System.out.println("Setting engaged");
         for( int i=0;i<4;i++)
            ch[i].setEngaged(true);
            
         System.out.println("\n\nSetting target position to 180 for 5 seconds\n\n");
         for( int i=0;i<4;i++)
            ch[i].setTargetPosition(180);
         Thread.sleep(5000);
            
         System.out.println("\n\nSetting target position to 0 for 5 seconds\n\n");
         for( int i=0;i<4;i++)
            ch[i].setTargetPosition(0);
        
         Thread.sleep(5000);
            
         System.out.println("\n\nSetting target position to 90 for 5 seconds\n\n");
         for( int i=0;i<4;i++)
            ch[i].setTargetPosition(90);
      
         Thread.sleep(5000);
      
         for( int i=0;i<4;i++)
         
            ch[i].close();
      
         System.out.println("\nClosed RCServo");
            
      } 
      catch (PhidgetException ex) {
         System.out.println(ex.getDescription());
      }
   }
}