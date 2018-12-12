class AbstractFactoryPattern {

	abstract class CPU {
		public abstract void getCPUDetail();
	}

	abstract class MMU {
		public abstract void getMMUDetail();
	}

	class AppleCPU extends CPU {
		@Override
		public void getCPUDetail() {
			System.out.println("Apple cpu is the best");
		}
	}

	class IntelCPU extends CPU {
		@Override
		public void getCPUDetail() {
			System.out.println("Intel cpu is the best");
		}		
	}

	class IntelMMU extends MMU {
		@Override
		public void getMMUDetail() {
			System.out.println("Intel MMU is the best");
		}
	}

	class IntelCPU extends MMU {
		@Override
		public void getMMUDetail() {
			System.out.println("Intel MMU is the best");
		}		
	}

	class AppleKit extends AbstractFactory {
	    @Override
	    public CPU createCPU() {
	        return new AppleCPU();
	    }

	    @Override
	    public MMU createMMU() {
	        return new AppleMMU();
	    }
	}

	class IntelKit extends AbstractFactory {
	    @Override
	    public CPU createCPU() {
	        return new IntelCPU();
	    }

	    @Override
	    public MMU createMMU() {
	        return new IntelMMU();
	    }		
	}

	enum Architecture {
	    INTEL, APPLE
	}

	abstract class AbstractFactory {
	    private static final AppleKit APPLE_KIT = new Applekit();
	    private static final IntelKit  INTEL_KIT = new Intelkit();

	    // Returns a concrete factory object that is an instance of the
	    // concrete factory class appropriate for the given architecture.
	    static AbstractFactory getFactory(Architecture architecture) {
	        AbstractFactory factory = null;
	        switch (architecture) {
	            case INTEL:
	                factory = INTEL_KIT;
	                break;
	            case APPLE:
	                factory = APPLE_KIT;
	                break;
	        }
	        return factory;
	    }   
	    public abstract CPU createCPU();
    	public abstrac	}t MMU createMMU();


    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Architecture.INTEL);
        CPU cpu = factory.createCPU();
        cpu.getCPUDetail();
    }
}