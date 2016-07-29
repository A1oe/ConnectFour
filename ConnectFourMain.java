import java.util.Scanner;

/**
 * 
 */

/**
 * @author atdp-11 Alyssa Lo
 *
 */
public class ConnectFourMain {

	/*
	 *  main - Makes menu & runs Other Programs
	 * 
	 *  @param args
	 * 
	 *  @return void
	 * 
	 *  Has while loop that makes sure user inputs valid integer
	 *  Has Four Loop That Creates Initial Grid
	 * 
	 */
	public static void main(String[] args) {
		// Connect 4 Main Menu

		Scanner console = new Scanner (System.in);
		String input = "";

		while (!input.equals("5")){
			// Headers
			System.out.println("Welcome to Connect Four.");
			System.out.println("(Enter: \"1\" - 2 player game, \"2\" - " +
					"VS A Dumb Computer, \"3\" - VS A Smart Computer, \"4\" " +
			"- Simulated Game, \"5\" - Quit)");


			input = console.nextLine();

			// More Headers To Check For Valid Inputs
			while (!input.equals("1") && !input.equals("2") && !input.equals("3") 
					&& !input.equals("4") && !input.equals("5")){

				System.out.println("Please Type In A Valid Integer.");
				System.out.println("(Enter: \"1\" - 2 player game, " +
						"\"2\" - VS A Dumb Computer, \"3\" - " +
				"VS A Smart Computer, \"4\" - Simulated Game, \"5\" - Quit)");

				input = console.nextLine();
			}

			// Initial Grid Of Game
			String [][] firstGrid = new String [6][7];
			for (int row = 0; row < firstGrid.length; row++){
				for (int col = 0; col < firstGrid[row].length; col++){
					firstGrid[row][col] = "-"; 
				}
			}

			// Actual Menu Button Pressing
			if (input.equals("1")){
				twoPlayer(firstGrid, console);

			} else if (input.equals("2")){
				dumbCom(firstGrid, console);

			} else if (input.equals("3")){
				smartCom(firstGrid, console);

			} else if (input.equals("4")){
				simulator(firstGrid, console);

			}
		}

		console.close();
	}

	/*
	 *  grid - Fills And Prints What The Current Grid Should Look Like
	 * 
	 *  @param: 2D String array parameter - The Values of the Current Board  
	 * 
	 *  @return: void
	 * 
	 *  Nested for loops For Printing 
	 * 
	 */
	public static void grid(String [][] fillGrid){
		for (int row = 0; row < fillGrid.length; row++){        
			for (int col = 0; col < fillGrid[row].length; col++){
				System.out.print(fillGrid[row][col] + " ");
			}

			System.out.println();
		}

		System.out.println("0 1 2 3 4 5 6\n"); // Help Make Clear What To Enter When Playing

	}

	/*
	 *  checkDownDiag - Checks Diagonals in \ direction
	 * 
	 *  @param - gridString : Looks At Array 
	 *              rowS : Checks At Row rowS
	 *            colS : Checks At Column colS
	 *              input : Whether It Is Checking For X or O
	 * 
	 *  @return - returns count (how many times input has been found)
	 * 
	 *  3 if Statements: Depending on Location of Element
	 *  Checking Above: for Loop That Checks Ones Above And To The Left 
	 *                  Until Either count >= 4 or No More Elements Are Left In That Direction
	 *  Checking Below: Same As Checking Above Except It Is Below And To The Right
	 * 
	 */
	public static int checkDownDiag (String [][] gridString, int rowS , int colS, int count, String input){

		// Checks Element Above And Below

		// Above
		for (int rDiag = rowS - 1, cDiag = colS - 1; rDiag > -1 && cDiag > -1; rDiag--, cDiag--){ 

			if (gridString[rDiag][cDiag].equals(input)){ // Check For Input
				count++;
			} else {
				rDiag = 0; // Stop Loops
			}

		}

		// Below
		for (int rowDiag = rowS + 1, colDiag = colS + 1; rowDiag < 6 && colDiag < 7; rowDiag++, colDiag++){ 
			if (gridString[rowDiag][colDiag].equals(input)){
				count++;
			} else {
				rowDiag = 5; // Stop Loops
			}
		}

		// Returns
		if (count >= 4){
			return count;
		} else {
			return 0;
		}
	}

	/*
	 *  checkUpDiag - Checks Diagonals in / direction
	 * 
	 *  @param - gridString : Looks At Array 
	 *              rowS : Checks At Row rowS
	 *            colS : Checks At Column colS
	 *              input : Whether It Is Checking For X or O
	 * 
	 *  @return - returns count (how many times input has been found)
	 *  
	 *  3 if Statements: Depending on Location of Element
	 *  Checking Above: for Loop That Checks Ones Above And To The Right 
	 *                  Until Either count >= 4 or No More Elements Are Left In That Direction
	 *  Checking Below: Same As Checking Above Except It Is Below And To The Left
	 * 
	 */
	public static int checkUpDiag (String [][] gridString, int rowS , int colS, int count, String input){

		// Checks Elements Above And Below
		// Above
		for (int rDiag = rowS - 1, cDiag = colS + 1; rDiag > -1 && cDiag < 7; rDiag--, cDiag++){ 

			if (gridString[rDiag][cDiag].equals(input)){
				count++;
			} else {
				rDiag = 0; // Stop loops
			}

			if (count >= 4){
				rDiag = 0; // Stop Loop
			}

		}

		// Below
		for (int rowDiag = rowS + 1, colDiag = colS - 1; rowDiag < 6 && colDiag > -1; rowDiag++, colDiag--){ 
			if (gridString[rowDiag][colDiag].equals(input)){
				count++;
			} else {
				rowDiag = 5; // Stop Loops
			}

			if (count >= 4){
				rowDiag = 5; // Stop Loop
			}
		}

		// Returns
		if (count >= 4){
			return count;

		} else {
			return 0;

		}
	}



	/*
	 *  checkWinner - Checks For Winners Diagonally, Horizontally, And Vertically
	 * 
	 *  @param: String [][] gridCheck - Checks if There Are Any 4 In A Rows
	 * 
	 *  @return: String winner - Returns A Win or Empty String
	 * 
	 *  Nested for Loops to Check Each Element 
	 *  if Statements To See If There Are X or O In A Row 
	 *     & To See If winner Needs To return A Win
	 *    
	 */
	public static String checkWinner(String [][] gridString){
		int countX = 0;
		int countO = 0;
		String winner = "";

		// Checks Horizontally 
		if(winner.equals("")){
			for (int rowH = 5; rowH > -1; rowH--){ // Goes Through All Elements
				for (int colH = 6; colH > -1; colH--){ // Starts Checking From Bottom Right
					if (gridString[rowH][colH].equals("X")){
						countX++;
						if (countO < 4){
							countO = 0;
						}
					} else if (gridString[rowH][colH].equals("O")){
						countO++;
						if (countX < 4){
							countX = 0;
						}
					} else if (countX < 4 && countO < 4){
						countO = 0;
						countX = 0;
					}
				}

				if (countX >= 4){
					winner = "Player 1 Wins!\n";
					rowH = 0; // Ends for Loop
				} else if (countO >= 4){
					winner = "Player 2 Wins!\n";
					rowH = 0; // Ends for Loop
				} else {
					countX = 0;
					countO = 0;
				}
			}
		}

		// Checks Vertically
		if (winner.equals("")){ // Only Run If Winner Still Hasn't Been Found
			for (int colV = 6; colV > -1; colV--){ // Goes Through All Elements
				for (int rowV = 5; rowV > -1; rowV--){ // Starts Checking From Bottom Right
					if (gridString[rowV][colV].equals("X")){
						countX++;
						countO = 0;
					} else if (gridString[rowV][colV].equals("O")){
						countO++;
						countX = 0;
					} else if (countX < 4 && countO < 4){
						countO = 0;
						countX = 0;
					}

				}

				if (countX >= 4){
					winner = "Player 1 Wins!\n";
					colV = 0; // Ends for Loop
				} else if (countO >= 4){
					winner = "Player 2 Wins!\n";
					colV = 0; // Ends for Loop
				} else {
					countX = 0;
					countO = 0;
				}
			}
		}

		// Checks Diagonals
		if (winner.equals("")){ // Only Run If Winner Still Hasn't Been Found
			for (int rowS = 5; rowS > -1; rowS--){ // Goes Through All Elements
				for (int colS = 6; colS > -1; colS--){ // Starts Checking From Bottom Right

					// ******************** Check X Positions *************************
					if (gridString[rowS][colS].equals("X")){
						countX = 1;

						// Check \ Diagonals
						int count = checkDownDiag(gridString, rowS , colS, countX, "X");

						if (count != 0){
							winner = "Player 1 Wins!\n";
							rowS = 0;
						} else {
							countX = 0;
						}

						// Check / Diagonals

						countX = 1;
						count = checkUpDiag(gridString, rowS , colS, countX, "X");

						if (count != 0){
							winner = "Player 1 Wins!\n";
							rowS = 0;
						} else {
							countX = 0;
						}


					}


					//******** Check O Positions ************************
					if (gridString[rowS][colS].equals("O")){
						countO = 1;

						// Check \ Diagonals
						int count = checkDownDiag(gridString, rowS , colS, countO, "O");

						if (count != 0){
							winner = "Player 2 Wins!\n";
							rowS = 0;
						} else {
							countO = 0;
						}

						// Check / Diagonals

						countO = 1;
						count = checkUpDiag(gridString, rowS , colS, countO, "O");

						if (count != 0){
							winner = "Player 2 Wins!\n";
							rowS = 0;
						} else {
							countO = 0;
						}

					}
				}

			}

		}

		return winner;

	}

	/*
	 *  twoPlayer - Runs Two Player Method Option
	 * 
	 *  @param: String [][] firstGrid - Brings In How Grid Should Initially Look Like,
	 *          Eventually Change This Every Time A Move Is Made
	 *         
	 *          Scanner console - Scanner For Inputs
	 *         
	 *  @return: void
	 * 
	 *  nested for loop: Goes Through Each To Help To See Any Empty Spaces
	 *  while loop: Check To See Any Empty Spaces
	 *  if statements: Which One To Run On Which Turn 
	 * 
	 */
	public static void twoPlayer (String [][] grid, Scanner console){
		int count = 0;
		int ender = 0;

		String one = "";
		String two = "";
		String oneSymb = "X";
		String twoSymb = "O";

		System.out.println("2 Player Mode\n");
		grid(grid);

		// Nested For Loop To Check For Empty Spaces
		for (int row = 0; row < grid.length; row++){        
			for (int col = 0; col < grid[row].length; col++){

				// While Loop To Check For Empty Spaces
				while (grid[row][col].equals("-") && ender == 0){

					// if Statement Run On 1st Player's Turn
					if (count % 2 == 0){
						System.out.println("Player 1, Type In A Column Number (0-6): ");
						one = console.nextLine();

						// if Statement To Check For Valid Inputs
						if (!one.equals("0") && !one.equals("1") && !one.equals("2") && 
								!one.equals("3") && !one.equals("4") && !one.equals("5") 
								&& !one.equals("6")){
							System.out.println("Invalid Input. Skipped To Next Player.\n");

							grid(grid);

							count++;

						} else {

							// for loop To Place Symbol In Next Available Row For Inputed Column
							int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
							for (int r = 5; r > -1 ; r--){    
								if (grid[r][Integer.valueOf(one)].equals("-") && hold == 0){
									grid[r][Integer.valueOf(one)] = oneSymb;
									hold++;

									grid(grid);

									if (!checkWinner(grid).equals("")){

										System.out.println(checkWinner(grid));

										ender++;
									} else {

										count++;
									}

								} else if (r == 0 && !grid[r][Integer.valueOf(one)].equals("-")){
									System.out.println("Invalid Input. Skipped To Next Player.\n");

									grid(grid);

									count++;
								}
							}    
						}
					}

					if (ender == 0){
						// if Statement Run On 2nd Player's Turn
						if (count % 2 == 1){
							System.out.println("Player 2, Type In A Column Number (0-6): ");
							two = console.nextLine();

							// if Statement To Check For Valid Inputs
							if (!two.equals("0") && !two.equals("1") && !two.equals("2") && 
									!two.equals("3") && !two.equals("4") && !two.equals("5")
									&& !two.equals("6")){
								System.out.println("Invalid Input. Skipped To Next Player.\n");

								grid(grid);

								count++;
							} else {

								// for loop To Place Symbol In Next Available Row For Inputed Column
								int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
								for (int r = 5; r > -1 ; r--){    
									if (grid[r][Integer.valueOf(two)].equals("-") && hold == 0){
										grid[r][Integer.valueOf(two)] = twoSymb;
										hold++;

										grid(grid);

										if (!checkWinner(grid).equals("")){
											System.out.println(checkWinner(grid));

											ender++;
										} else {

											count++;
										}

									} else if (r == 0 && !grid[r][Integer.valueOf(two)].equals("-")){
										System.out.println("Invalid Input. Skipped To Next Player.\n");
										grid(grid);

										count++;
									}
								}
							}
						}
					}
				}
			}
		}

		if (ender == 0){
			System.out.println("Tie Game.\n");
		}

	}

	/*
	 * simulator : Simulates gameplay between smart and dumb computer
	 * 
	 * @param - String [][] grid - Gives Grids 
	 *          Scanner console - Scanner For Inputs
	 *          
	 * @return - void
	 *  
	 * 
	 *  
	 */
	public static void simulator(String[][] grid, Scanner console) {
		// TODO Auto-generated method stub
		System.out.println("Connect Four Simulation Game.\nPress Enter.");
		String nextButton = console.nextLine(); // Press Space For Next Moves

		int count = 0;
		int ender = 0;

		String oneSymb = "X";
		String twoSymb = "O";

		grid(grid);

		// Nested For Loop To Check For Empty Spaces
		for (int row = 0; row < grid.length; row++){        
			for (int col = 0; col < grid[row].length; col++){

				// While Loop To Check For Empty Spaces
				while (grid[row][col].equals("-") && ender == 0){

					// if Statement Run On Dumb Computer's Turn
					if (count % 2 == 0){

						System.out.println("Dumb Computer's Turn. (X)\n");

						// for loop To Place Symbol In Next Available Row For Inputed Column
						int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
						int randomCol = (int)(Math.random() * 7); // Random Int For Column Number

						for (int r = 5; r > -1 ; r--){    
							if (grid[r][randomCol].equals("-") && hold == 0){
								grid[r][randomCol] = oneSymb;
								hold++;

								grid(grid);

								if (checkWinner(grid).equals("Player 2 Wins!\n")){

									System.out.println("Dumb Computer Wins!\n");

									ender++;
								} else {

									count++;
								}

							} else if (r == 0 && !grid[r][randomCol].equals("-")){
								System.out.println("Invalid Input. Skipped To Next Player.\n");
								grid(grid);

								count++;
							}
						}

					}

					System.out.println("Press Enter: ");
					nextButton = console.nextLine();

					if (ender == 0){ // Only Run This Part If Player Hasn't Won Yet
						// if Statement Run On Smart Computer's Turn
						if (count % 2 == 1){

							System.out.println("Smart Computer's Turn. (O)\n");

							int hold = 0; // Holder Variable, Used for Quitting Out of for loop
							int comColumn = 1000; // Where Marker Will Be Placed

							// Checks If There Is Good Spot (3 in Row for O) 
							for (int colCheck = 0; colCheck < 7; colCheck++){ 
								// Place Marker In Next Available Row
								for (int r = 5; r > -1 ; r--){   

									if (grid[r][colCheck].equals("-") && hold == 0){
										grid[r][colCheck] = twoSymb;
										hold++;

										String checker = checkWinner(grid);

										if (checker.equals("Player 2 Wins!\n")){
											comColumn = colCheck;
											grid[r][colCheck] = "-";
											colCheck = 6;

										}  else {

											grid[r][colCheck] = "-";
										}
										r = 0;
									}
								}
								hold = 0;
							}

							// Checks If There Is Good Spot (3 in Row for X)
							if (comColumn == 1000){
								for (int colCheck = 0; colCheck < 7; colCheck++){ 
									// Place Marker In Next Available Row
									for (int r = 5; r > -1 ; r--){    
										if (grid[r][colCheck].equals("-") && hold == 0){
											grid[r][colCheck] = oneSymb;
											hold++;

											String checker = checkWinner(grid);
											if (checker.equals("Player 1 Wins!\n")){
												comColumn = colCheck;
												grid[r][colCheck] = "-";
												colCheck = 6;

											} else {

												grid[r][colCheck] = "-";
											}
										}
									}
									hold = 0;
								}
							}

							// Run If There Is No Better Spot (No 3 In Rows)
							if (comColumn == 1000){
								hold = 0; // Used To Stop For Loop
								comColumn = (int)(Math.random() * 7); // Put In Random Spot

								// Makes Sure It Is In Valid Random Spot
								for (int r = 5; r > -1 ; r--) {    
									if (grid[r][comColumn].equals("-") && hold == 0) {
										hold++;

									} else if (r == 0 && !grid[r][comColumn].equals("-")) {
										comColumn = (int)(Math.random() * 7);
										r = 5;
									}
								}
								hold = 0;
							}

							// for Loop To Put Computer's Mark In Next Available Row
							for (int r = 5; r > -1 ; r--){    
								if (grid[r][comColumn].equals("-") && hold == 0){
									grid[r][comColumn] = twoSymb;
									hold++;

									grid(grid);

									if (!checkWinner(grid).equals("") && !checkWinner(grid).equals("Player 1 Wins!\n")){

										System.out.println("Smart Computer Wins!\n");

										ender++;
									} else {

										count++;
									}                              
								}
							}
						}
					}

					System.out.println("Press Enter: ");
					nextButton = console.nextLine();
				}
				// Tie Game If No One Has Won And No Element Has '-' 
				if (ender == 0){
					System.out.println("Tie Game.\n");
				}
			}
		}
	}

	/*
	 *  smartCom - smartCom Can 3 horizontal or vertical block or diagonal & will win if 3 in a row
	 * 
	 *  @param - String [][] grid - Gives Grids 
	 *         
	 *           Scanner console - Scanner For Inputs
	 *          
	 *  @return - void
	 *  
	 *  nested for loop: Goes Through Each To Help To See Any Empty Spaces
	 *  while loop: Check To See Any Empty Spaces
	 *  if statements: Which One To Run On Which Turn
	 *  another for loop : Checks Each Possible Position To See If There Will Be A Win On Either Side
	 *  
	 */
	public static void smartCom(String[][] grid, Scanner console) {
		int count = 0;
		int ender = 0;

		String one = "";
		String oneSymb = "X";
		String twoSymb = "O";

		System.out.println("Player VS Smart Computer\n");
		grid(grid);

		// Nested For Loop To Check For Empty Spaces
		for (int row = 0; row < grid.length; row++){        
			for (int col = 0; col < grid[row].length; col++){

				// While Loop To Check For Empty Spaces
				while (grid[row][col].equals("-") && ender == 0){

					// if Statement Run On Player's Turn
					if (count % 2 == 0){
						System.out.println("Player's Turn.\nType In A Column Number (0-6): ");
						one = console.nextLine();

						// if Statement To Check For Valid Inputs
						if (!one.equals("0") && !one.equals("1") && !one.equals("2") && 
								!one.equals("3") && !one.equals("4") && !one.equals("5") && !one.equals("6")){
							System.out.println("Invalid Input. Skipped To Computer.\n");

							grid(grid);

							count++;

						} else {

							// for loop To Place Symbol In Next Available Row For Inputed Column
							int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
							for (int r = 5; r > -1 ; r--){ // Puts Player's Marker At Highest Row   
								if (grid[r][Integer.valueOf(one)].equals("-") && hold == 0){
									grid[r][Integer.valueOf(one)] = oneSymb;
									hold++;

									grid(grid);

									if (!checkWinner(grid).equals("") && !checkWinner(grid).equals("Player 2 Wins!\n")){

										System.out.println("Player Wins!\n");

										ender++;
									} else {

										count++;
									}

								} else if (r == 0 && !grid[r][Integer.valueOf(one)].equals("-")){
									System.out.println("Invalid Input. Skipped To Next Player.\n");

									grid(grid);

									count++;
								}
							}    
						}

						if (ender == 0){ // Only Run This Part If Player Hasn't Won Yet
							// if Statement Run On Computer's Turn
							if (count % 2 == 1){

								System.out.println("Computer's Turn.\n");

								int hold = 0; // Holder Variable, Used for Quitting Out of for loop
								int comColumn = 1000; // Where Marker Will Be Placed

								// Checks If There Is Good Spot (3 in Row for O) 
								for (int colCheck = 0; colCheck < 7; colCheck++){ 
									// Place Marker In Next Available Row
									for (int r = 5; r > -1 ; r--){   

										if (grid[r][colCheck].equals("-") && hold == 0){
											grid[r][colCheck] = twoSymb;
											hold++;

											String checker = checkWinner(grid);

											if (checker.equals("Player 2 Wins!\n")){
												comColumn = colCheck;
												grid[r][colCheck] = "-";
												colCheck = 6;

											}  else {

												grid[r][colCheck] = "-";
											}
											r = 0;
										}
									}
									hold = 0;
								}

								// Checks If There Is Good Spot (3 in Row for X)
								if (comColumn == 1000){
									for (int colCheck = 0; colCheck < 7; colCheck++){ 
										// Place Marker In Next Available Row
										for (int r = 5; r > -1 ; r--){    
											if (grid[r][colCheck].equals("-") && hold == 0){
												grid[r][colCheck] = oneSymb;
												hold++;

												String checker = checkWinner(grid);
												if (checker.equals("Player 1 Wins!\n")){
													comColumn = colCheck;
													grid[r][colCheck] = "-";
													colCheck = 6;

												} else {

													grid[r][colCheck] = "-";
												}
											}
										}
										hold = 0;
									}
								}

								// Run If There Is No Better Spot (No 3 In Rows)
								if (comColumn == 1000){
									hold = 0; // Used To Stop For Loop
									comColumn = (int)(Math.random() * 7); // Put In Random Spot

									// Makes Sure It Is In Valid Random Spot
									for (int r = 5; r > -1 ; r--) {    
										if (grid[r][comColumn].equals("-") && hold == 0) {
											hold++;

										} else if (r == 0 && !grid[r][comColumn].equals("-")) {
											comColumn = (int)(Math.random() * 7);
											r = 5;
										}
									}
									hold = 0;
								}

								// for Loop To Put Computer's Mark In Next Available Row
								for (int r = 5; r > -1 ; r--){    
									if (grid[r][comColumn].equals("-") && hold == 0){
										grid[r][comColumn] = twoSymb;
										hold++;

										grid(grid);

										if (!checkWinner(grid).equals("") && !checkWinner(grid).equals("Player 1 Wins!\n")){

											System.out.println("Smart Computer Wins!\n");

											ender++;
										} else {

											count++;
										}                              
									}
								}
							}
						}
					}
				}
			}
		}

		// Tie Game If No One Has Won And No Element Has '-' 
		if (ender == 0){
			System.out.println("Tie Game.\n");
		}
	}

	/*
	 *  dumbCom - Player Against Random Columns
	 * 
	 *  @param: String [][] grid:  Makes Grids
	 *             Scanner console: Scans For Inputs
	 * 
	 *  @return: void
	 * 
	 *  nested for loop: Goes Through Each To Help To See Any Empty Spaces
	 *  while loop: Check To See Any Empty Spaces
	 *  if statements: Which One To Run On Which Turn 
	 * 
	 */
	public static void dumbCom(String[][] grid, Scanner console) {
		int count = 0;
		int ender = 0;

		String one = "";
		String oneSymb = "X";
		String twoSymb = "O";

		System.out.println("Player VS Dumb Computer\n");
		grid(grid);

		// Nested For Loop To Check For Empty Spaces
		for (int row = 0; row < grid.length; row++){        
			for (int col = 0; col < grid[row].length; col++){

				// While Loop To Check For Empty Spaces
				while (grid[row][col].equals("-") && ender == 0){

					// if Statement Run On Player's Turn
					if (count % 2 == 0){
						System.out.println("Player's Turn.\nType In A Column Number (0-6): ");
						one = console.nextLine();

						// if Statement To Check For Valid Inputs
						if (!one.equals("0") && !one.equals("1") && !one.equals("2") && 
								!one.equals("3") && !one.equals("4") && !one.equals("5") && !one.equals("6")){
							System.out.println("Invalid Input. Skipped To Computer.\n");

							grid(grid);

							count++;

						} else {

							// for loop To Place Symbol In Next Available Row For Inputed Column
							int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
							for (int r = 5; r > -1 ; r--){    
								if (grid[r][Integer.valueOf(one)].equals("-") && hold == 0){
									grid[r][Integer.valueOf(one)] = oneSymb;
									hold++;

									grid(grid);

									if (!checkWinner(grid).equals("") && !checkWinner(grid).equals("Player 2 Wins!\n")){

										System.out.println("Player Wins!\n");

										ender++;
									} else {

										count++;
									}

								} else if (r == 0 && !grid[r][Integer.valueOf(one)].equals("-")){
									System.out.println("Invalid Input. Skipped To Next Player.\n");

									grid(grid);

									count++;
								}
							}    
						}

						if (ender == 0){ // Only Run This Part If Player Hasn't Won Yet
							// if Statement Run On Computer's Turn
							if (count % 2 == 1){

								System.out.println("Computer's Turn.\n");

								// for loop To Place Symbol In Next Available Row For Inputed Column
								int hold = 0; // Holder Variable, Used for Quitting Out of Following for loop
								int randomCol = (int)(Math.random() * 7); // Random Int For Column Number

								for (int r = 5; r > -1 ; r--){    
									if (grid[r][randomCol].equals("-") && hold == 0){
										grid[r][randomCol] = twoSymb;
										hold++;

										grid(grid);

										if (checkWinner(grid).equals("Player 2 Wins!\n")){

											System.out.println("Dumb Computer Wins!\n");

											ender++;
										} else {

											count++;
										}

									} else if (r == 0 && !grid[r][randomCol].equals("-")){
										System.out.println("Invalid Input. Skipped To Next Player.\n");
										grid(grid);

										count++;
									}
								}
							}
						}
					}
				}
			}
		}

		if (ender == 0){
			System.out.println("Tie Game.\n");
		}
	}
}