package molecule;

public class Hydrogen extends Thread {

	private static int hydrogenCounter = 0;
	private int id;
	private Propane sharedPropane;


	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter += 1;
		id = hydrogenCounter;
		this.sharedPropane = propane_obj;

	}

	public void run() {
		try {
			sharedPropane.mutex.acquire();
			sharedPropane.addHydrogen();
			//hydrogenCounter+=1;
			/**
			 * checks if the number of hydrogens and carbons are 8 and 3
			 * */
			//(sharedPropane.getHydrogen() >= 8) && (sharedPropane.getCarbon() >= 3)
			if ((sharedPropane.getHydrogen() >= 8) && (sharedPropane.getCarbon() >= 3)) {
				System.out.println("---Group ready for bonding---");

				/**
				 * release 8 hydrogens atoms then remove them after molecule is formed
				 * */
				sharedPropane.hydrogensQ.release(8);
				sharedPropane.removeHydrogen(8);

				//sharedPropane.barrier.phase1();

				/**
				 * release 3 carbons atoms then remove them after molecule is formed
				 * */
				sharedPropane.carbonQ.release(3);
				sharedPropane.removeCarbon(3);

				//sharedPropane.barrier.phase2();
			} else {
				/**
				 * release lock
				 * */
				sharedPropane.mutex.release();
			}

			sharedPropane.hydrogensQ.acquire();
			sharedPropane.bond("H" + this.id);
			/**
			 * wait for other threads
			 * */

			sharedPropane.barrier.phase1();
			sharedPropane.barrier.phase2();
			sharedPropane.mutex.release();


				/*sharedPropane.hydrogensQ.release(8);
				sharedPropane.removeHydrogen(8);
				sharedPropane.carbonQ.release();
				sharedPropane.removeCarbon(3);*/
		}
			/*else{

				sharedPropane.mutex.release();
			}*/
		//sharedPropane.hydrogensQ.acquire();
			/*sharedPropane.mutex.acquire();
	    	sharedPropane.bond("H"+ this.id);
			sharedPropane.removeCarbon(1);
			sharedPropane.mutex.release();

			sharedPropane.barrier.b_wait();

			sharedPropane.mutex.acquire();//---------m
			sharedPropane.addCarbon();
			if(sharedPropane.getCarbon()==0){
				sharedPropane.addCarbon();
				sharedPropane.carbonQ.release();
			}
			sharedPropane.mutex.release();//---------m
			//sharedPropane.hydrogensQ.release();*/


	   catch (InterruptedException ex) { /* not handling this  */}
	    //System.out.println(" ");
	}

}
