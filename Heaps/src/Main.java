import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//Initialise the list with single list of random zipcodes 
		List<List<Integer>> zipCodesList1 = List.of(
				                List.of(6,8,14,17,15,16,20)
				            );
		
		//Initialise the list with lists(increasing in size) of random zipcodes for Experimental Analysis 
		List<List<Integer>> zipCodesList2 = List.of(
						                List.of(12,5),
						                List.of(4,3,1),
						                List.of(7,9,13,19,18),
						                List.of(21,22,23,11,2,10),
						                List.of(6,8,14,17,15,16,20)
						            );
		
		int operation = 0;
		
		//Perform operations depending upon the input operation
		while(operation != 5) {
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------------");
			
			System.out.println("Enter the operation number you want to perform from the list below : "
					+ "\n1.\tInsert data into Priority Queue (PQ), Adaptable PQ, Sorted PQ, Unsorted PQ | Remove Minimum Element and View the latest Minimum Element"
					+ "\n2.\tExperimental Analysis - Insertion"
					+ "\n3.\tExperimental Analysis - Remove Min"
					+ "\n4.\tExperimental Analysis - View Min"
					+"\n5.\tEnd\n");
			BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
			operation = Integer.parseInt(reader.readLine());
			switch(operation) {
			
			case 1: System.out.println("\n"+"**************************************************************************************************************");
				    System.out.println("\n"+"\t\t\t\t\t\tInserting Data");
				    System.out.println("\n"+"**************************************************************************************************************");
				    System.out.println("\n\t\t\t\t\t1.Priority Queue\n\t\t\t\t\t2.Adaptable Priority Queue"
					+ "\n\t\t\t\t\t3.Sorted Priority Queue\n\t\t\t\t\t4.Unsorted Priority Queue\t\t\t\t\t\t\t\t\t\t");
			        System.out.println("\n"+"**************************************************************************************************************");
				    heapImplementations(1,zipCodesList1,1);
				    System.out.println("\n"+"**************************************************************************************************************");
				    break;
			
			case 2: System.out.println("\n"+"**************************************************************************************************************");
			   	    System.out.println("\n"+"\t\t\t\t\t\tEXPERIMENTAL ANALYSIS - Insertion\t\t\t\t\t\t\t\t\t\t");
			        System.out.println("\n"+"**************************************************************************************************************");
			        heapImplementations(5,zipCodesList2,2);
			        System.out.println("\n"+"**************************************************************************************************************");
			        break;
				   
			case 3: System.out.println("\n"+"**************************************************************************************************************");
	   	    		System.out.println("\n"+"\t\t\t\t\t\tEXPERIMENTAL ANALYSIS - Remove Min\t\t\t\t\t\t\t\t\t\t");
	   	    		System.out.println("\n"+"**************************************************************************************************************");
	   	    		experimentalAnalysisRemoval(5,zipCodesList2,true);
	   	    		System.out.println("\n"+"**************************************************************************************************************");
	   	    		break;
			       
			case 4: System.out.println("\n"+"**************************************************************************************************************");
	    			System.out.println("\n"+"\t\t\t\t\t\tEXPERIMENTAL ANALYSIS - View Min\t\t\t\t\t\t\t\t\t\t");
	    			System.out.println("\n"+"**************************************************************************************************************");
	    			experimentalAnalysisRemoval(5,zipCodesList2,false);
	    			System.out.println("\n"+"**************************************************************************************************************");
	    			break;
		   	   		
			case 5: System.out.println("Process Completed, System Exiting!");
				    System.exit(0); // Exit the system
			 	    break;
			
			default : System.out.println("Invalid operation");
					  break;
			}
			
		}
	}

	/*
	 * This method is responsible for inserting the data into the Priority Queue(PQ), Adaptable PQ, Sorted PQ, Unsorted PQ
	 * And Performing experimental analysis for insertions
	 * @param numberOfIterations : 1 initial run, 5 for experimental analysis
	 * @param zipCodesList : List of Lists of integer zip codes for which data has to be processed
	 * @param operation : To distinguish whether it is an initial run or experimental analysis
	 */
	private static void heapImplementations(int numberOfIterations, List<List<Integer>> zipCodesList,int operation) {
		
		HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue = new HeapPriorityQueue<>(); //initializing HeapPriorityQueue
		HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq = new HeapAdaptablePriorityQueue<>();//initializing HeapAdaptablePriorityQueue
		SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq = new SortedPriorityQueue<>();//initializing SortedPriorityQueue
		UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq = new UnsortedPriorityQueue<>();//initializing UnsortedPriorityQueue
		
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap that stores the zipcodes and its corressponding crime incidents data
		
		 
		listOfCrimeIncidentsForZipMap = fetchDataFromCsv(); // fetch data from the csv
		
		int processedElements = 0;
		
		Map<Integer,CrimeIncidentsData> refinedListOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap to store the filtered zip codes to be processed and its corresponding data
		System.out.println("\t\t\tPriority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			long timeToInsertInPq = 0;
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			//Experimental Analysis : Perform 5 iterations (of insertion) on the same data and calculate the average time taken to insert the record in Priority Queue(PQ)
			//Other cases : Only 1 iteration
			for(int j = 0; j<numberOfIterations;j++) {
				
				long startTimeToInsertInPq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
								
					//Insert into priority queue, priority being the count of crime incidents for the zipcode
					priorityQueue.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
					
				});
				long endTimeToInsertInPq = System.nanoTime();
				
				timeToInsertInPq += endTimeToInsertInPq - startTimeToInsertInPq;	
				//Count of number of elements being processed
				processedElements = priorityQueue.size();

				//Only for experimental analysis empty the priority queue before starting the next iteration
				if(operation == 2) {
					while(priorityQueue.removeMin()!= null) {
						priorityQueue.removeMin();
					}
				}
				
			}
			
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInPq/numberOfIterations));
		}
		
		if(operation == 1) {
			//For Operation 1, remove the minimum value element from the priority queue and display the next minimimum value element
			removeMinFromPQ(priorityQueue);
		}
		//re-initialize the hashmap for the Adaptable priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//re-initialize the processed elements count for the Adaptable priority queue
		processedElements = 0;
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAdaptable Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			long timeToInsertInAdaptablePq = 0;
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			//Experimental Analysis : Perform 5 iterations (of insertion) on the same data and calculate the average time taken to insert the record in Adaptable Priority Queue(PQ)
			//Other cases : Only 1 iteration
			for(int j = 0; j<numberOfIterations;j++) {
								
				long startTimeToInsertInAdaptablePq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				//Insert into adaptable priority queue, priority being the count of crime incidents for the zipcode
				adaptablePq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				
				});
				long endTimeToInsertInAdaptablePq = System.nanoTime();
			
				timeToInsertInAdaptablePq += endTimeToInsertInAdaptablePq - startTimeToInsertInAdaptablePq;
				
				//Count of number of elements being processed
				processedElements = adaptablePq.size();
				
				//Only for experimental analysis empty the priority queue before starting the next iteration
				if(operation == 2) {
					while(adaptablePq.removeMin()!= null) {
						adaptablePq.removeMin();
					}
				}
				
							
			}	
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInAdaptablePq/numberOfIterations));
		}
				
		if(operation == 1) {
			removeMinFromPQ(adaptablePq);			
		}
		
		//re-initialize the hashmap for the Sorted priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//re-initialize the processed elements count for the Sorted priority queue
		processedElements = 0;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSorted Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			long timeToInsertInSortedPq = 0;
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			//Experimental Analysis : Perform 5 iterations (of insertion) on the same data and calculate the average time taken to insert the record in Sorted Priority Queue(PQ)
			//Other cases : Only 1 iteration
			for(int j = 0; j<numberOfIterations;j++) {
			
				long startTimeToInsertInSortedPq = System.nanoTime();
				
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				//Insert into sorted priority queue, priority being the count of crimeincidents in the zipcode
				sortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
				
				long endTimeToInsertInSortedPq = System.nanoTime();
				
				timeToInsertInSortedPq += endTimeToInsertInSortedPq - startTimeToInsertInSortedPq;	
				
				//Count of number of elements being processed
				processedElements = sortedPq.size();
				
				//Only for experimental analysis empty the priority queue before starting the next iteration
				if(operation == 2) {
					while(sortedPq.removeMin()!= null) {
						sortedPq.removeMin();
					}
				}
				
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInSortedPq/numberOfIterations));

		}
		if(operation == 1) {
			//For Operation 1, remove the minimum value element from the sorted priority queue and display the next minimimum value element
			removeMinFromPQ(sortedPq);
		}
		
		//re-initialize the hashmap for the Unsorted priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//re-initialize the processed elements count for the Unsorted priority queue
		processedElements = 0;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tUnsorted Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			long timeToInsertInUnsortedPq = 0;
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			//Experimental Analysis : Perform 5 iterations (of insertion) on the same data and calculate the average time taken to insert the record in Unsorted Priority Queue(PQ)
			//Other cases : Only 1 iteration
			for(int j = 0; j<numberOfIterations;j++) {							
				long startTimeToInsertInUnsortedPq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				//Insert into unsorted priority queue, priority being the count of crimeincidents in the zipcode
				unsortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
				long endTimeToInsertInUnsortedPq = System.nanoTime();
				timeToInsertInUnsortedPq += endTimeToInsertInUnsortedPq - startTimeToInsertInUnsortedPq;
				//Count of number of elements being processed
				processedElements = unsortedPq.size();
				
				//Only for experimental analysis empty the priority queue before starting the next iteration
				if(operation == 2) {
					while(unsortedPq.removeMin()!= null) {
						unsortedPq.removeMin();
					}
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInUnsortedPq/numberOfIterations));

		}
		
		if(operation == 1) {
			//For Operation 1, remove the minimum value element from the unsorted priority queue and display the next minimimum value element
			removeMinFromPQ(unsortedPq);
		}
		
	}

	/*
	 * This method is responsible for performing the experimental analysis of Removing the minimum element from the priority queues
	 * It calculates the average time to remove the minimum element from each of the priority queues as the number of elements increase in the priority queue
	 * @param numberOfIterations : 5
	 * @zipCodesList : List of list of integer zipcodes
	 */
	private static void experimentalAnalysisRemoval(int numberOfIterations,List<List<Integer>> zipCodesList,boolean isRemoveMin) {
		
		HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue = new HeapPriorityQueue<>(); // initialize HeapPriorityQueue
		HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq = new HeapAdaptablePriorityQueue<>(); // initialize HeapAdaptablePriorityQueue
		SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq = new SortedPriorityQueue<>();//initialize SortedPriorityQueue
		UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq = new UnsortedPriorityQueue<>();//initialize UnsortedPriorityQueue

		int processedElements = 0;
		
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap for storing zip code and corresponding data
		
		 
		listOfCrimeIncidentsForZipMap = fetchDataFromCsv(); // fetch data from csv
		
		Map<Integer,CrimeIncidentsData> refinedListOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap to store zip code data that needs to be processed
		System.out.println("\t\t\tPriority Queue");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			//Initialize time to remove from the priority queues
			long timeToRemoveFromPq = 0;
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					//Filter out the data for the zipcodes present in the zipcode list and store in a new map
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					//Insert into priority queue, priority being the count of crimeincidents in the zipcode
					priorityQueue.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				//Count of data being processed
				processedElements = priorityQueue.size();
				
				//If experimental analysis for removeMin()
				if(isRemoveMin) {
					long startTimeToRemoveFromPq = System.nanoTime();
					priorityQueue.removeMin();
					long endTimeToRemoveFromPq = System.nanoTime();
					timeToRemoveFromPq += endTimeToRemoveFromPq - startTimeToRemoveFromPq;
				}
				else {
					//If experimental analysis for min()
					long startTimeToRemoveFromPq = System.nanoTime();
					priorityQueue.min();
					long endTimeToRemoveFromPq = System.nanoTime();
					timeToRemoveFromPq += endTimeToRemoveFromPq - startTimeToRemoveFromPq;
				}
				
				//Empty the priority queue before next iteration
				while(priorityQueue.removeMin()!= null) {
					priorityQueue.removeMin();
				}
			}
			
			System.out.println("\t\t"+processedElements+"\t\t|\t"+(double)(timeToRemoveFromPq/numberOfIterations));
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAdaptable Priority Queue");
		System.out.println("------------------------------------------------------------------------");
		
		//Re-initialize hashmap for adaptable priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//Re-initialize processed elements count for adaptable priority queue
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			long timeToRemoveFromAdaptablePq = 0;
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					//Insert into adaptable priority queue, priority being the count of crimeincidents in the zipcode
					adaptablePq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				//Count of data being processed
				processedElements = adaptablePq.size();
				
				//If experimental analysis for removeMin()
				if(isRemoveMin) {
					long startTimeToRemoveFromAdaptablePq = System.nanoTime();
					adaptablePq.removeMin();
					long endTimeToRemoveFromAdaptablePq = System.nanoTime();
					timeToRemoveFromAdaptablePq += endTimeToRemoveFromAdaptablePq - startTimeToRemoveFromAdaptablePq;
				}
				else {
					//If experimental analysis for min()
					long startTimeToRemoveFromAdaptablePq = System.nanoTime();
					adaptablePq.min();
					long endTimeToRemoveFromAdaptablePq = System.nanoTime();
					timeToRemoveFromAdaptablePq += endTimeToRemoveFromAdaptablePq - startTimeToRemoveFromAdaptablePq;
				}
				
				//Empty the adaptable priority queue before next iteration
				while(adaptablePq.removeMin()!= null) {
					adaptablePq.removeMin();
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToRemoveFromAdaptablePq/numberOfIterations));
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSorted Priority Queue");
		System.out.println("------------------------------------------------------------------------");
		
		//Re-initialize the hashmap for sorted priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//Re-initialize processed elements count for sorted priority queue
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			
			long timeToRemoveFromSortedPq = 0;
					
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					//Insert into sorted priority queue, priority being the count of crimeincidents in the zipcode
					sortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
				
				//Count of data being processed
				processedElements = sortedPq.size();
				
				//If experimental analysis for removeMin()
				if(isRemoveMin) {
					long startTimeToRemoveFromSortedPq = System.nanoTime();
					sortedPq.removeMin();
					long endTimeToRemoveFromSortedPq = System.nanoTime();
					timeToRemoveFromSortedPq += endTimeToRemoveFromSortedPq - startTimeToRemoveFromSortedPq;
				}
				else {
					//If experimental analysis for min()
					long startTimeToRemoveFromSortedPq = System.nanoTime();
					sortedPq.min();
					long endTimeToRemoveFromSortedPq = System.nanoTime();
					timeToRemoveFromSortedPq += endTimeToRemoveFromSortedPq - startTimeToRemoveFromSortedPq;
				}
				
				while(sortedPq.removeMin()!= null) {
					sortedPq.removeMin();
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToRemoveFromSortedPq/numberOfIterations));
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tUnsorted Priority Queue");
		System.out.println("------------------------------------------------------------------------");
		//re-initialize the hashmap for the unsorted priority queue
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		//re-initialize the number of processed Elements for the unsorted priority queue
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			long timeToRemoveFromUnsortedPq = 0;

			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				//Filter out the data for the zipcodes present in the zipcode list and store in a new map
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					//Insert into unsorted priority queue, priority being the count of crimeincidents in the zipcode
					unsortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				//Count of data being processed
				processedElements = unsortedPq.size();
				
				//If experimental analysis for removeMin()
				if(isRemoveMin) {
					long startTimeToRemoveFromUnsortedPq = System.nanoTime();
					unsortedPq.removeMin();
					long endTimeToRemoveFromUnsortedPq = System.nanoTime();
					timeToRemoveFromUnsortedPq += endTimeToRemoveFromUnsortedPq - startTimeToRemoveFromUnsortedPq;
				}
				else {
					//If experimental analysis for min()
					long startTimeToRemoveFromUnsortedPq = System.nanoTime();
					unsortedPq.min();
					long endTimeToRemoveFromUnsortedPq = System.nanoTime();
					timeToRemoveFromUnsortedPq += endTimeToRemoveFromUnsortedPq - startTimeToRemoveFromUnsortedPq;
				}
				
				//Empty the queue before next iteration
				while(unsortedPq.removeMin()!= null) {
					unsortedPq.removeMin();
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToRemoveFromUnsortedPq/numberOfIterations));
			
		}
	}

	/*
	 * This method reads the csv file and creates a Map with Key = Zip code and Value = Object containting the list 
	 * crime incidents for the zip code and the total count of crime incidents for the zip code
	 */
	private static Map<Integer, CrimeIncidentsData> fetchDataFromCsv() {
		FileManager fileManager = new FileManager(); //Initialize the FileManager class
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>(); // initilize hashmap to store the data
		for(CrimeIncidents crime: fileManager.getCrimeData()) {
			//If map contains the zip code already, update its count and list of crime incidents
			if(listOfCrimeIncidentsForZipMap.containsKey(crime.getZipCode())) {
				
				CrimeIncidentsData crimeIncidentsData = listOfCrimeIncidentsForZipMap.get(crime.getZipCode());
				crimeIncidentsData.setCount(crimeIncidentsData.getCount()+1);
				crimeIncidentsData.getCrimeIncidents().add(crime);
				listOfCrimeIncidentsForZipMap.put(crime.getZipCode(),crimeIncidentsData);
				
			}else {
				//If map doesnt contain zip code, add a entry into the map with count =1 and list containing the crime incident data
				LinkedList<CrimeIncidents> crimeIncidentsList  = new LinkedList<>();
				crimeIncidentsList.add(crime);
				CrimeIncidentsData crimeIncidentsData = new CrimeIncidentsData(1, crimeIncidentsList);
				listOfCrimeIncidentsForZipMap.put(crime.getZipCode(), crimeIncidentsData);
				
			}
		}
		
		return listOfCrimeIncidentsForZipMap;
	}
	
	/*
	 * Removes the minimum element and displays the new minimum
	 */
	private static void removeMinFromPQ(HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");

		System.out.println("Removing Zip Code :"+priorityQueue.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Priority Queue"
				+"| Number of Crime Incidents : "+priorityQueue.min().getKey());
				priorityQueue.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+priorityQueue.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+priorityQueue.min().getKey());
		
	}
	
	/*
	 * Removes the minimum element and displays the new minimum
	 */
	
	private static void removeMinFromPQ(HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code :"+adaptablePq.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Adaptable Priority Queue"
				+"| Number of Crime Incidents : "+adaptablePq.min().getKey());
		adaptablePq.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+adaptablePq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+adaptablePq.min().getKey());

	}

	/*
	 * Removes the minimum element and displays the new minimum
	 */
	private static void removeMinFromPQ(SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code :"+sortedPq.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Sorted Priority Queue"
				+"| Number of Crime Incidents : "+sortedPq.min().getKey());
		sortedPq.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+sortedPq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+sortedPq.min().getKey());
	}
	
	/*
	 * Removes the minimum element and displays the new minimum
	 */
	private static void removeMinFromPQ(UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code : "+unsortedPq.min().getValue().get(0).getZipCode()+
				" with minimum crime incidents from Unsorted Priority Queue"
				+"| Number of Crime Incidents : "+unsortedPq.min().getKey());
		unsortedPq.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+unsortedPq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+unsortedPq.min().getKey());

	}

}
