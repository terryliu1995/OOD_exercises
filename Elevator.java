class Question1 {
	// First ask the interviewer what kind of elevator?  
	// there is only one elevator serving that building or multiple elevators serving the building simultaneously?
	// this situation is that: there is one elevator serving the building.  there are many floors in the buliding. 
	// Maybe there are some users in different floor pressing the button simultaneously. 
	// This results in some requests to RequestProcessCenter for processing. 
	// The  RequestProcessCenter figure out the first request that need to be processed in such an algorithm 
	// that the distance between target floor and current floor is shortest.
	public class Elevator {
		private int currentFloor;
		private int targetFloor;
		private int status; // 0 is stop, 1 is up, 2 is down;
		//只有一个电梯，singleton, 而且可能同时有好多人在用，多线程，volatile
		private static volatile instance = null；
		private Elevator() {
    		this.currentFloor = 0;
    		this.targetFloor = 0;
    		this.status = 0;
  		}
 		//用户可以获取电梯
		public static Elevator getElevator() {
			if (instance == null) {
				Sychronized(this.class) { //Sychronized(this.class)对该类的所有实例都进行了同步
					if (instance == null) {
						instence = new Elevator();
					}
				}
			}
			return instance;
		}

		public int getCurrentFloor() {
			return currentFloor;
		}

		public void setTargetFloor(int targetFloor) {
			this.targetFloor  = targetFloor;
		}

		public int getStatus() {
			return status;
		}

		public int gsetStatus(int status_) {
			this.status = status_;
		}

		public void moveTo(int targetFloor) {
			while (currentFloor < targetFloor) {
				moveUp();
				status = 1;
			}
			while (currentFloor > targetFloor) {
				moveDown();
				status = -1;
			}
			//status?
		}

		private void moveUp()  {
			currentFloor ++;
		}

		private void moveDown() {
			currentFloor --;
		}
	}


	public class User {
		private int targetFloor;
		private int srcFloor;
		private User(int targetFloor, int srcFloor) {
			this.targetFloor = targetFloor;
			this.srcFloor = srcFloor;
		}
		public void makeRequest() {
			RequestHandler.getInstance().addRequest(new Request(targetFloor, srcFloor));
		}
	}

	public class Reqest {
		private int targetFloor;
		private int srcFloor;
		private boolean isTarget = false;
		private Request(int targetFloor, int srcFloor) {
			this.targetFloor = targetFloor;
			this.srcFloor = srcFloor;
		}

		public boolean getIsTarget() {
			return isTarget;
		}
		public boolean setIsTarget() {
			this.isTarget = true;
		}
		public int getFloor() {
			if (isTarget) {
				return targetFloor;
			} else {
				return srcFloor;
			}
		}
 	}

	public class ControlCenter {
		List<Request> requests;
		//只有一个control center, singleton
		private static volatile ControlCenter instance = null;
		ControlCenter() {
			requests = new LinkedList<Request>();
		}

		public static ControlCenter getControlCenter() {
			if (instance == null) {
				Sychronized(this.class) {
					if (instance == null) {
						instance = new ControCenter
					}
				}
			}
			return instance;
		}

		public void addRequest(targetFloor, srcFloor) {
			requests.add(new Request(targetFloor, srcFloor));
		}

		public void processRequest() {
			Request req;
			while (!requests.isEmpty()) {
				req = getARequest();
				Elevator.getInstance().moveTo(req.getFloor());

				if (req.getStatus()) {
					requests.remove(req);
				} else {
					req.setStatus = true;
				}

			}
			Elevator.getInstance().setStatus(0);
		}

		public Request getARequest() {
			Elevator elevator = Elevator.getInstance();
			int currentFloor = elevator.getCurrentFloor();
			int curDir = elevator.getStatus();
			int distance = Integet.MAX_VALUE;
			Request ans = null;
			//找个最近楼层
			if (curDir > 0) {
				//找上面最近的；
			} else if（curDir < 0） {
				//找下面最近的
			} else {
				//找个最近的
			}
		}
	}

}