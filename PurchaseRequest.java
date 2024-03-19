package org.example;

public class PurchaseRequest {

        private double amount;
        private String type;

        public PurchaseRequest(double amount, String type) {
            this.amount = amount;
            this.type = type;
        }

        public double getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }
    }

    // Handler interface
    interface Approver {
        void setNext(Approver nextApprover);
        void processRequest(PurchaseRequest request);
    }

    // Concrete handler: Manager
    class Manager implements Approver {
        private Approver nextApprover;

        @Override
        public void setNext(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if (request.getAmount() <= 1000) {
                System.out.println("Manager approves " + request.getType() + " request of $" + request.getAmount());
            } else if (nextApprover != null) {
                nextApprover.processRequest(request);
            } else {
                System.out.println("No one can handle the " + request.getType() + " request.");
            }
        }
    }

    // Concrete handler: Director
    class Director implements Approver {
        private Approver nextApprover;

        @Override
        public void setNext(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if (request.getAmount() <= 5000) {
                System.out.println("Director approves " + request.getType() + " request of $" + request.getAmount());
            } else if (nextApprover != null) {
                nextApprover.processRequest(request);
            } else {
                System.out.println("No one can handle the " + request.getType() + " request.");
            }
        }
    }

    // Concrete handler: VicePresident
    class VicePresident implements Approver {
        private Approver nextApprover;

        @Override
        public void setNext(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if (request.getAmount() <= 10000) {
                System.out.println("Vice President approves " + request.getType() + " request of $" + request.getAmount());
            } else if (nextApprover != null) {
                nextApprover.processRequest(request);
            } else {
                System.out.println("No one can handle the " + request.getType() + " request.");
            }
        }
    }

    // Concrete handler: President
    class President implements Approver {
        private Approver nextApprover;

        @Override
        public void setNext(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if (request.getAmount() <= 50000) {
                System.out.println("President approves " + request.getType() + " request of $" + request.getAmount());
            } else {
                System.out.println("Purchase request of $" + request.getAmount() + " exceeds President's authority.");
            }
        }
    }

    // Client class
    public class ComplexChainOfResponsibilityExample {
        public static void main(String[] args) {
            // Creating the chain of responsibility
            Approver manager = new Manager();
            Approver director = new Director();
            Approver vp = new VicePresident();
            Approver president = new President();

            // Setting up the chain
            manager.setNext(director);
            director.setNext(vp);
            vp.setNext(president);

            // Test different types of purchase requests
            PurchaseRequest request1 = new PurchaseRequest(500, "Office Supplies"); // Manager should approve
            PurchaseRequest request2 = new PurchaseRequest(4500, "Equipment"); // Director should approve
            PurchaseRequest request3 = new PurchaseRequest(12000, "Marketing Campaign"); // VP should approve
            PurchaseRequest request4 = new PurchaseRequest(60000, "Company Event"); // President should approve

            // Process requests
            manager.processRequest(request1);
            manager.processRequest(request2);
            manager.processRequest(request3);
            manager.processRequest(request4);
        }
    }

}
