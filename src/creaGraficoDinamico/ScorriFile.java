package creaGraficoDinamico;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartUtils;
// import org.jfree.chart.JFreeChart;
// import org.jfree.data.statistics.HistogramDataset;
//http://www.java2s.com/Code/Java/Chart/JFreeChartCombinedCategoryPlotDemo1.htm

public class ScorriFile {
	
	 static Properties prop = new Properties();
	 
	 XYDataset dataset;
	 XYSeriesCollection datasetSeries;
	 TimeSeries timeSeries = new TimeSeries("Files Inseriti");
	 TimeSeriesCollection datasetTemporale = null;
	 CreaGrafo creaGrafo = null;
	 
	
	 public static void main(String[] args) throws IOException {
	
		 ScorriFile main_obj = new ScorriFile();
		 main_obj.creaGrafo = new CreaGrafo();
		
		 
		  try (InputStream input = new FileInputStream(".\\resources\\properties.prop")) {

	          	prop.load(input);
	           
	            System.out.println(prop.getProperty("path.inseriti"));
	            System.out.println(prop.getProperty("path.daInserire"));
	            // System.out.println(prop.getProperty("db.password"));

	        		} catch (IOException ex) {
	        									ex.printStackTrace();
	        								}
		  		String pathAvro = prop.getProperty("path.inseriti");
		  		ArrayList<File> directories = new ArrayList<File>(
				    Arrays.asList(
				        new File(pathAvro).listFiles(File::isDirectory)
				    )
				);
		
		  		String pathAvroComplete = prop.getProperty("path.totaleGenerale");
		  		ArrayList<File> directoriesComplete = new ArrayList<File>(
				    Arrays.asList(
				        new File(pathAvroComplete).listFiles(File::isDirectory)
				    )
				);
		  		
		  		 XYSeries series = new XYSeries("Test");
		  		 TimeSeries timeSeries = new TimeSeries("Files Inseriti");
		  		TimeSeries timeSeriesDaInserire = new TimeSeries("Files Da Inserire");
		  		long count;
		  		long countComplete;
		  		List<Avro> data = new ArrayList<Avro>();
		  		List<CalcoloDifferenze> dataDifferenze = new ArrayList<CalcoloDifferenze>();
		  		
		  		
		  	for (File num : directories) { 		
		  		
	           System.out.println(num); 
	           try (Stream<Path> files = Files.list(Paths.get(num.getAbsolutePath()))) {
			    count = files.count();
			 
			    System.out.println("Sulla directory  " + num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)  + " vi sono " + count + " files");
			    SimpleDateFormat f = new SimpleDateFormat("MMdd");
		         try {
					timeSeries.add(new Day(f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1))), count);
					String meseGiorno = f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)).toString();
					data.add( new Avro(meseGiorno,count) );
					//data.add(f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}  
	           } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	      }
		  
			for (File num : directoriesComplete) { 		      
		           System.out.println(num); 
		           try (Stream<Path> filesComplete = Files.list(Paths.get(num.getAbsolutePath()))) {
				    countComplete = filesComplete.count();
				   // System.out.println("!! Sulla directory  " + num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)  + " vi sono " + countComplete + " files");
				    SimpleDateFormat f = new SimpleDateFormat("MMdd");
			         try {
			        	 timeSeriesDaInserire.add(new Day(f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1))), countComplete);
			        	 
			        	 for (int counter = 0; counter < data.size(); counter++) { 
			        		 
			        		 Avro p = data.get(counter);
			                 if (f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)).toString().equalsIgnoreCase(p.getMeseAnno()))
			                 {
			                	 System.out.println("HO Matchato" + (countComplete-p.getNumeroS()));
			                	 // cd.setMeseAnno( f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)).toString());
			                	 // cd.setNumeroS(countComplete-p.getNumeroS());
			                	 dataDifferenze.add( new CalcoloDifferenze(f.parse(num.getAbsolutePath().substring(num.getAbsolutePath().lastIndexOf("\\")+1)).toString(),countComplete-p.getNumeroS()) );

			                 }
			             }   	
			        	 
					} catch (ParseException e) {
						e.printStackTrace();
					}  
		           } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		  	
		  	 	
				XYSeriesCollection dataset = new XYSeriesCollection();
		        dataset.addSeries(series);
		      
		        main_obj.creaGrafo.setSerieCollection(dataset);
		        main_obj.creaGrafo.setSeries(series);
		        main_obj.creaGrafo.setTimeseriesOK(timeSeries);
		        main_obj.creaGrafo.setTimeseriesDaInserire(timeSeriesDaInserire);
		  	
		  	
		  	 XYDataset serie = null;
			try {
				
				serie = main_obj.creaGrafo.createDatasetData();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     JFreeChart chart = main_obj.creaGrafo.createChart(serie);
		     
		     
		     
		   
		     CategoryDataset serieDue = main_obj.creaGrafo.createDatasetDataDaInserire(dataDifferenze);
			     JFreeChart chartDue = main_obj.creaGrafo.createChartDue(serieDue);   
		     
		     
		     
		     org.jfree.chart.ChartUtilities.saveChartAsPNG(new File("line_chartDue.png"), chartDue, 400, 400);
		     org.jfree.chart.ChartUtilities.saveChartAsPNG(new File("line_chart.png"), chart, 400, 400);
		

		
	}
	
	public static Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
	    Set<String> fileSet = new HashSet<>();
	 
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
	        for (Path path : stream) {
	            if (!Files.isDirectory(path)) {
	                fileSet.add(path.getFileName()
	                    .toString());
	            }
	        }
	    }
	    return fileSet;
	}
	

}
