import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

// Example of using getopt to process command line arguments and options
public class Main {
    public static void main(String[] args) {
        // store the mode for running this program
        String mode = "";

        // specify the options that we want to support in this program
        LongOpt[] longOptions = {
                new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
                new LongOpt("mode", LongOpt.REQUIRED_ARGUMENT, null, 'm')
        };
        // make a Getopt object to process the args variable
        //constructor: name, the args array, "short option string"
        // -list all the short options (last arg to the LongOpt constructor
        // - int the same order as the array
        // - for any LongOpt with a REQUIRED_ARGUMENT, put a ':' after this letter
        //long options array
        Getopt g = new Getopt("Lab1", args, "hm:", longOptions);
        //enable error output
        g.setOpterr(true);

        //Getopt will give us one command line option at a time.
        //keep track of the current option we are processing (Getopt give this to us as
        // an int, but it's technically the character of the short option

        int choice;

        //g.getopt() returns the char/int short flag
        // or -1 if there are no more to process
        while((choice = g.getopt())!= -1){
            // choice contains a single command line option for us to process
            // a switch allows us to specify code to execute for various values of
            // a variable
            switch (choice) {
                case 'h':
                    printHelp();
                    System.exit(0);
                    break;
                case 'm':
                    mode = g.getOptarg();
                    // error check to ensure a valid mode
                    if (!mode.equals("average") && !mode.equals("median")){
                        System.err.println("Error: invalid mode" + mode);
                        System.exit(1);
                }
                    break;
                default:
                    System.err.println("Error: invalid option");
                    System.exit(1);
                    break;
            }//switch
        }//while
        // Processing command line arguments is done.
        // TO-DO check that the mode is actually set

        // Let's actually run our program now.
        System.out.printf("The mode is: %s\n", mode);

    }//main

    private static void printHelp() {
        System.out.println("Usage: java [options] Main [-m average|median] [-h]");
        System.out.println("This program is an example of processing command line");
        System.out.println("arguments with Getopt.");
    }
}
