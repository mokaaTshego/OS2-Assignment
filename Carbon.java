package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Propane sharedPropane;
	
	public Carbon(Propane propane_obj) {
		Carbon.carbonCounter+=1;
		id=carbonCounter;
		this.sharedPropane = propane_obj;
	}
	
	public void run() {
	    try {
			sharedPropane.mutex.acquire();
			/**
			 * accesses the carbon atom from methane
			 * */
			sharedPropane.addCarbon();
			//carbonCounter+=1;
			//(sharedPropane.getHydrogen() >= 8) && (sharedPropane.getCarbon() >= 3)


			if ((sharedPropane.getHydrogen() >= 8) && (sharedPropane.getCarbon() >= 3)){
				System.out.println("---Group ready for bonding---");
				/**
				 * releases the 8 hydrogen to form Propane
				 * */
				sharedPropane.hydrogensQ.release(8);
				/**
				 * remove the 8 hydrogen after forming  1 Propane
				 * */
				sharedPropane.removeHydrogen(8);
				//sharedPropane.barrier.phase1();
				/**
				 * releases the 3 carbons to form Propane
				 * */

				sharedPropane.carbonQ.release(3);
				/**
				 * removes the 3 carbons from molecule
				 * */
				sharedPropane.removeCarbon(3);
				//sharedPropane.barrier.phase2();
			}
			else{
				/**
				 * thread releases lock
				 */
				sharedPropane.mutex.release();
			}

			sharedPropane.carbonQ.acquire();
			sharedPropane.bond("C"+ this.id);


			sharedPropane.barrier.phase1();
			sharedPropane.barrier.phase2();
			sharedPropane.mutex.release();


			//sharedPropane.addCarbon();
			/*if (sharedPropane.getHydrogen() >= 8) {
				// you will need to fix below
				System.out.println("---Group ready for bonding---");
				//System.out.println("hhhhh");
				sharedPropane.hydrogensQ.release(8);
				sharedPropane.removeHydrogen(8);
				sharedPropane.carbonQ.release();
				sharedPropane.removeCarbon(3);

			}
			else{

				sharedPropane.mutex.release();
			}*/
			//sharedPropane.carbonQ.acquire();
			/*sharedPropane.mutex.acquire();
	    	sharedPropane.bond("C"+ this.id);  //bond
			//sharedPropane.barrier.b_wait();
			sharedPropane.removeCarbon(3);
			sharedPropane.mutex.release();
			sharedPropane.barrier.b_wait();

			sharedPropane.mutex.acquire(); //thread must acquire lock
			sharedPropane.addCarbon();
			if(sharedPropane.getCarbon()==0){
				sharedPropane.addCarbon();
				sharedPropane.addCarbon();
				sharedPropane.addCarbon();
				sharedPropane.carbonQ.release(3);
			}
			sharedPropane.mutex.release();
			//sharedPropane.carbonQ.release(); */



		}
	    catch (InterruptedException ex) { /* not handling this  */}
	   // System.out.println(" ");
	}

}
