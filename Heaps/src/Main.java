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
			        heapImplementations(5,zipCodesList2,0);
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
	 */
	private static void heapImplementations(int numberOfIterations, List<List<Integer>> zipCodesList,int operation) {
		
		HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue = new HeapPriorityQueue<>(); //initializing HeapPriorityQueue
		HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq = new HeapAdaptablePriorityQueue<>();//initializing HeapAdaptablePriorityQueue
		SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq = new SortedPriorityQueue<>();//initializing SortedPriorityQueue
		UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq = new UnsortedPriorityQueue<>();//initializing UnsortedPriorityQueue
		
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap that stores the zipcodes and its corressponding crime incidents data
		
		 
		listOfCrimeIncidentsForZipMap = fetchDataFromCsv(); // fetch data from the csv
		
		//Initialize the time taken by each data structure to perform insertion
		long timeToInsertInPq = 0;
		long timeToInsertInAdaptablePq = 0;
		long timeToInsertInSortedPq = 0;
		long timeToInsertInUnsortedPq = 0;
		
		int processedElements = 0;
		
		Map<Integer,CrimeIncidentsData> refinedListOfCrimeIncidentsForZipMap = new HashMap<>(); //Hashmap to store the zip codes and its corressponding data
		System.out.println("\t\t\tPriority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			for(int j = 0; j<numberOfIterations;j++) {
				
				long startTimeToInsertInPq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
								
					//Insert into priority queue, priority being the count of crimeincidents in the zipcode
					priorityQueue.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
					
				});
				long endTimeToInsertInPq = System.nanoTime();
				
				timeToInsertInPq += endTimeToInsertInPq - startTimeToInsertInPq;	
				processedElements = priorityQueue.size();

				if(operation == 2) {
					while(priorityQueue.removeMin()!= null) {
						priorityQueue.removeMin();
					}
				}
				
			}
			
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInPq/numberOfIterations));
		}
		
		
		
		
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		
		processedElements = 0;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAdaptable Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			
			for(int j = 0; j<numberOfIterations;j++) {
								
				long startTimeToInsertInAdaptablePq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				
				adaptablePq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				
				});
				long endTimeToInsertInAdaptablePq = System.nanoTime();
			
				timeToInsertInAdaptablePq += endTimeToInsertInAdaptablePq - startTimeToInsertInAdaptablePq;
				processedElements = adaptablePq.size();
				
				if(operation == 2) {
					while(adaptablePq.removeMin()!= null) {
						adaptablePq.removeMin();
					}
				}
				
							
			}	
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInAdaptablePq/numberOfIterations));
		}
				
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		
		processedElements = 0;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSorted Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			for(int j = 0; j<numberOfIterations;j++) {
			
				//System.out.println("Iteration Number : "+(i+1)+" Number of elements in Sorted Priority Queue : "+ refinedListOfCrimeIncidentsForZipMap.size());
				long startTimeToInsertInSortedPq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				//Insert into sorted priority queue, priority being the count of crimeincidents in the zipcode
				sortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
				
				long endTimeToInsertInSortedPq = System.nanoTime();
				
				timeToInsertInSortedPq += endTimeToInsertInSortedPq - startTimeToInsertInSortedPq;	
				
				processedElements = sortedPq.size();
				
				if(operation == 2) {
					while(sortedPq.removeMin()!= null) {
						sortedPq.removeMin();
					}
				}
				
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInSortedPq/numberOfIterations));

		}

		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		processedElements = 0;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tUnsorted Priority Queue\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
			
			for(int j = 0; j<numberOfIterations;j++) {							
				//System.out.println("Iteration Number : "+(i+1)+" Number of elements in Unsorted Priority Queue : "+ refinedListOfCrimeIncidentsForZipMap.size());
				long startTimeToInsertInUnsortedPq = System.nanoTime();
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
				
				unsortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
				long endTimeToInsertInUnsortedPq = System.nanoTime();
				timeToInsertInUnsortedPq += endTimeToInsertInUnsortedPq - startTimeToInsertInUnsortedPq;
				processedElements = unsortedPq.size();
				
				if(operation == 2) {
					while(unsortedPq.removeMin()!= null) {
						unsortedPq.removeMin();
					}
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToInsertInUnsortedPq/numberOfIterations));

		}
		
		if(operation == 1) {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			removeMinFromPQ(priorityQueue);
			removeMinFromPQ(adaptablePq);
			removeMinFromPQ(sortedPq);
			removeMinFromPQ(unsortedPq);
		}
		
	}

	private static void experimentalAnalysisRemoval(int numberOfIterations,List<List<Integer>> zipCodesList,boolean isRemoveMin) {
		
		HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue = new HeapPriorityQueue<>();
		HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq = new HeapAdaptablePriorityQueue<>();
		SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq = new SortedPriorityQueue<>();
		UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq = new UnsortedPriorityQueue<>();

		
		long timeToRemoveFromPq = 0;
		long timeToRemoveFromAdaptablePq = 0;
		long timeToRemoveFromSortedPq = 0;
		long timeToRemoveFromUnsortedPq = 0;
		int processedElements = 0;
		
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>();
		
		 
		listOfCrimeIncidentsForZipMap = fetchDataFromCsv();
		
		Map<Integer,CrimeIncidentsData> refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		System.out.println("\t\t\tPriority Queue");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					priorityQueue.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				processedElements = priorityQueue.size();
				
				if(isRemoveMin) {
					long startTimeToRemoveFromPq = System.nanoTime();
					priorityQueue.removeMin();
					long endTimeToRemoveFromPq = System.nanoTime();
					timeToRemoveFromPq += endTimeToRemoveFromPq - startTimeToRemoveFromPq;
				}
				else {
					long startTimeToRemoveFromPq = System.nanoTime();
					//System.out.println("Least risk zip code : "+priorityQueue.min().getValue().get(0).getZipCode());
					long endTimeToRemoveFromPq = System.nanoTime();
					timeToRemoveFromPq += endTimeToRemoveFromPq - startTimeToRemoveFromPq;
				}
				
				while(priorityQueue.removeMin()!= null) {
					priorityQueue.removeMin();
				}
			}
			
			System.out.println("\t\t"+processedElements+"\t\t|\t"+(double)(timeToRemoveFromPq/numberOfIterations));
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tAdaptable Priority Queue");
		System.out.println("------------------------------------------------------------------------");
		
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					adaptablePq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				processedElements = adaptablePq.size();
				
				if(isRemoveMin) {
					long startTimeToRemoveFromAdaptablePq = System.nanoTime();
					adaptablePq.removeMin();
					long endTimeToRemoveFromAdaptablePq = System.nanoTime();
					timeToRemoveFromAdaptablePq += endTimeToRemoveFromAdaptablePq - startTimeToRemoveFromAdaptablePq;
				}
				else {
					long startTimeToRemoveFromAdaptablePq = System.nanoTime();
					//System.out.println("Least risk zip code : "+adaptablePq.min().getValue().get(0).getZipCode());
					long endTimeToRemoveFromAdaptablePq = System.nanoTime();
					timeToRemoveFromAdaptablePq += endTimeToRemoveFromAdaptablePq - startTimeToRemoveFromAdaptablePq;
				}
				
				while(adaptablePq.removeMin()!= null) {
					adaptablePq.removeMin();
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToRemoveFromAdaptablePq/numberOfIterations));
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t\t\tSorted Priority Queue");
		System.out.println("------------------------------------------------------------------------");
		
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		
		for(int i = 0;i<zipCodesList.size();i++) {
			
			
			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					sortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				processedElements = sortedPq.size();
				
				if(isRemoveMin) {
					long startTimeToRemoveFromSortedPq = System.nanoTime();
					sortedPq.removeMin();
					long endTimeToRemoveFromSortedPq = System.nanoTime();
					timeToRemoveFromSortedPq += endTimeToRemoveFromSortedPq - startTimeToRemoveFromSortedPq;
				}
				else {
					long startTimeToRemoveFromSortedPq = System.nanoTime();
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
		
		refinedListOfCrimeIncidentsForZipMap = new HashMap<>();
		processedElements = 0;
		
		System.out.println("Number of element\t\t|\t\tTime Taken");
		for(int i = 0;i<zipCodesList.size();i++) {
			

			for(Map.Entry<Integer, CrimeIncidentsData> entry : listOfCrimeIncidentsForZipMap.entrySet()) {
				if(zipCodesList.get(i).contains(entry.getKey())) {
					refinedListOfCrimeIncidentsForZipMap.put(entry.getKey(), entry.getValue());
				}
			}
		
			for(int j = 0; j<numberOfIterations;j++) {
				refinedListOfCrimeIncidentsForZipMap.entrySet().forEach(entry->{
					unsortedPq.insert(entry.getValue().getCount(),entry.getValue().getCrimeIncidents());
				});
					
				processedElements = unsortedPq.size();
				
				if(isRemoveMin) {
					long startTimeToRemoveFromUnsortedPq = System.nanoTime();
					unsortedPq.removeMin();
					long endTimeToRemoveFromUnsortedPq = System.nanoTime();
					timeToRemoveFromUnsortedPq += endTimeToRemoveFromUnsortedPq - startTimeToRemoveFromUnsortedPq;
				}
				else {
					long startTimeToRemoveFromUnsortedPq = System.nanoTime();
					//System.out.println("Least risk zip code : "+unsortedPq.min().getValue().get(0).getZipCode());
					long endTimeToRemoveFromUnsortedPq = System.nanoTime();
					timeToRemoveFromUnsortedPq += endTimeToRemoveFromUnsortedPq - startTimeToRemoveFromUnsortedPq;
				}
				
				while(unsortedPq.removeMin()!= null) {
					unsortedPq.removeMin();
				}
			}
			System.out.println("\t\t"+processedElements+"\t\t|\t\t"+(double)(timeToRemoveFromUnsortedPq/numberOfIterations));
			
		}
	}

	private static Map<Integer, CrimeIncidentsData> fetchDataFromCsv() {
		FileManager fileManager = new FileManager();
		Map<Integer,CrimeIncidentsData> listOfCrimeIncidentsForZipMap = new HashMap<>();
		for(CrimeIncidents crime: fileManager.getCrimeData()) {
			if(listOfCrimeIncidentsForZipMap.containsKey(crime.getZipCode())) {
				
				CrimeIncidentsData crimeIncidentsData = listOfCrimeIncidentsForZipMap.get(crime.getZipCode());
				crimeIncidentsData.setCount(crimeIncidentsData.getCount()+1);
				crimeIncidentsData.getCrimeIncidents().add(crime);
				listOfCrimeIncidentsForZipMap.put(crime.getZipCode(),crimeIncidentsData);
				
			}else {
				
				LinkedList<CrimeIncidents> crimeIncidentsList  = new LinkedList<>();
				crimeIncidentsList.add(crime);
				CrimeIncidentsData crimeIncidentsData = new CrimeIncidentsData(1, crimeIncidentsList);
				listOfCrimeIncidentsForZipMap.put(crime.getZipCode(), crimeIncidentsData);
				
			}
		}
		
		return listOfCrimeIncidentsForZipMap;
	}
	
	private static void removeMinFromPQ(HeapPriorityQueue<Integer,LinkedList<CrimeIncidents>> priorityQueue) {
		System.out.println("Removing Zip Code :"+priorityQueue.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Priority Queue"
				+"| Number of Crime Incidents : "+priorityQueue.min().getKey());
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		priorityQueue.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+priorityQueue.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+priorityQueue.min().getKey());
		
	}
	
	private static void removeMinFromPQ(HeapAdaptablePriorityQueue<Integer,LinkedList<CrimeIncidents>> adaptablePq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code :"+adaptablePq.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Adaptable Priority Queue"
				+"| Number of Crime Incidents : "+adaptablePq.min().getKey());
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		adaptablePq.removeMin();
		System.out.println("New Zip Code with minimum crime incidents : "+adaptablePq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+adaptablePq.min().getKey());

	}

	private static void removeMinFromPQ(SortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> sortedPq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code :"+sortedPq.min().getValue().get(0).getZipCode()+" with minimum crime incidents from Sorted Priority Queue"
				+"| Number of Crime Incidents : "+sortedPq.min().getKey());
		sortedPq.removeMin();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("New Zip Code with minimum crime incidents : "+sortedPq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+sortedPq.min().getKey());
	}
	
	private static void removeMinFromPQ(UnsortedPriorityQueue<Integer,LinkedList<CrimeIncidents>> unsortedPq) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Removing Zip Code : "+unsortedPq.min().getValue().get(0).getZipCode()+
				" with minimum crime incidents from Unsorted Priority Queue"
				+"| Number of Crime Incidents : "+unsortedPq.min().getKey());
		unsortedPq.removeMin();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("New Zip Code with minimum crime incidents : "+unsortedPq.min().getValue().get(0).getZipCode()+" | Number of Crime Incidents :"+unsortedPq.min().getKey());

	}

}
