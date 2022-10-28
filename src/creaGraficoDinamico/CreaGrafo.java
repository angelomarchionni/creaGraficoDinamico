package creaGraficoDinamico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CreaGrafo extends JFrame {
	
	
	TimeSeries timeseriesOK = new TimeSeries("Files Inseriti");
	TimeSeries timeseriesDaInserire = new TimeSeries("Files Da Inserire");
	 public TimeSeries getTimeseriesDaInserire() {
		return timeseriesDaInserire;
	}

	public void setTimeseriesDaInserire(TimeSeries timeseriesDaInserire) {
		this.timeseriesDaInserire = timeseriesDaInserire;
	}

	public TimeSeries getTimeseriesOK() {
		return timeseriesOK;
	}

	public void setTimeseriesOK(TimeSeries timeseriesOK) {
		this.timeseriesOK = timeseriesOK;
	}




	XYSeries series;
	 public XYSeriesCollection getSerieCollection() {
		return serieCollection;
	}

	public void setSerieCollection(XYSeriesCollection serieCollection) {
		this.serieCollection = serieCollection;
	}




	XYSeriesCollection serieCollection;
//	    public LineChartEx() {
//
//	        initUI();
//	    }

	    public XYSeries getSeries() {
		return series;
	}

	public void setSeries(XYSeries series) {
		this.series = series;
	}

		public void initUI() throws ParseException {


//			XYDataset dataset = createDatasetData();
//	        JFreeChart chart = createChart(dataset);
//
//	        ChartPanel chartPanel = new ChartPanel(chart);
//	        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//	        chartPanel.setBackground(Color.red);
//	        add(chartPanel);
//
//	        pack();
//	        setTitle("Grafico a Linea");
//	        setLocationRelativeTo(null);
//	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }


	    
	    public JFreeChart createChart(XYDataset dataset) {


	    	JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Numero Files per Data",
	                "Data",
	                "Numero files",
                dataset,
                true,
	                true,
	                false
	        );
	    	
	    	
	    	
	    	
	    	XYPlot plot = chart.getXYPlot();
	        plot.configureDomainAxes();
	       
	        final TickUnits standardUnits = new TickUnits();
	        standardUnits.add(new NumberTickUnit(1));
	        standardUnits.add(new NumberTickUnit(5));
	        standardUnits.add(new NumberTickUnit(10));
	        standardUnits.add(new NumberTickUnit(15));
	        standardUnits.add(new NumberTickUnit(20));
	        standardUnits.add(new NumberTickUnit(25));
	        
	        
	        
	        
	        //  XYPlot xyPlot = chart.getXYPlot();
	       ValueAxis domainAxis = plot.getDomainAxis();
	        ValueAxis rangeAxis = plot.getRangeAxis();

	   //   domainAxis.setRange(1, 10);
	   //    domainAxis.setTickUnit(new NumberTickUnit(1));
	     // rangeAxis.setRange(1, 20);
	     rangeAxis.setStandardTickUnits(standardUnits);
	    //   rangeAxis.setTickUnit(new NumberTickUnit(1));
	       
	       

	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	

	        plot.setRenderer(renderer);
	        plot.setBackgroundPaint(Color.GRAY);

	        plot.setRangeGridlinesVisible(true);
	        plot.setRangeGridlinePaint(Color.BLACK);

	        plot.setDomainGridlinesVisible(true);
	        plot.setDomainGridlinePaint(Color.BLACK);

	        //chart.getLegend().setAnchor(BlockBorder.NONE);

	        chart.setTitle(new TextTitle("Files per giorno",
	                        new Font("Serif", java.awt.Font.BOLD, 18)
	                )
	        );

	        return chart;
	    }
	   
	  
	    public JFreeChart createChartDue(CategoryDataset  serieDue) {


	    	JFreeChart chartDue = ChartFactory.createBarChart(
	    			"Differenza inserimento", // chart title
	    			"Data", // domain axis label
	    			"Numero files", // range axis label
	    			serieDue, // data
	    			PlotOrientation.VERTICAL,
	    			true, // include legend
	    			true, // tooltips?
	    			false // URLs?
	    			);
	    	

	    	
	    	
//	    	XYPlot plot = chartDue.getXYPlot();
//	        plot.configureDomainAxes();
//	       
//	        final TickUnits standardUnits = new TickUnits();
//	        standardUnits.add(new NumberTickUnit(1));
//	        standardUnits.add(new NumberTickUnit(5));
//	        standardUnits.add(new NumberTickUnit(10));
//	        standardUnits.add(new NumberTickUnit(15));
//	        standardUnits.add(new NumberTickUnit(20));
//	        standardUnits.add(new NumberTickUnit(25));
//	        
//	        
//	        
//	        
//	        //  XYPlot xyPlot = chart.getXYPlot();
//	       ValueAxis domainAxis = plot.getDomainAxis();
//	        ValueAxis rangeAxis = plot.getRangeAxis();
//
//	   //   domainAxis.setRange(1, 10);
//	   //    domainAxis.setTickUnit(new NumberTickUnit(1));
//	     // rangeAxis.setRange(1, 20);
//	     rangeAxis.setStandardTickUnits(standardUnits);
//	    //   rangeAxis.setTickUnit(new NumberTickUnit(1));
//	       
//	       
//
//	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//	
//
//	        plot.setRenderer(renderer);
//	        plot.setBackgroundPaint(Color.GRAY);
//
//	        plot.setRangeGridlinesVisible(true);
//	        plot.setRangeGridlinePaint(Color.BLACK);
//
//	        plot.setDomainGridlinesVisible(true);
//	        plot.setDomainGridlinePaint(Color.BLACK);
//
//	        //chart.getLegend().setAnchor(BlockBorder.NONE);
//
//	        chartdue.setTitle(new TextTitle("Files per giorno",
//	                        new Font("Serif", java.awt.Font.BOLD, 18)
//	                )
//	        );

	        return chartDue;
	    }
	   
	    
	    
	    public XYDataset createDatasetData() throws ParseException {
	     
	    
	        
	       TimeSeriesCollection dataset = new TimeSeriesCollection();
	       dataset.addSeries(getTimeseriesOK());
	       dataset.addSeries(getTimeseriesDaInserire());
	       return dataset;
	    }

	   
		     
		    
	    	public CategoryDataset createDatasetDataDaInserire(List<CalcoloDifferenze> dataDifferenze) {
	    		String series1 = "Differenza di files inseriti";
	    		// row keys...
	    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    		 for (int counter = 0; counter < dataDifferenze.size(); counter++) { 
	        		 
	        		 CalcoloDifferenze p = dataDifferenze.get(counter);
	        		 System.out.println(p.getMeseAnno().substring(4, 10));
	        		 dataset.addValue(p.getNumeroS(),series1,  p.getMeseAnno().substring(4, 10));
	        		
	    		 }
	    		
	    		
	    		
	    	
//	    		// String series2 = "Second";
//	    		// String series3 = "Third";
//	    		// column keys...
//	    		String category1 = "Data 1";
//	    		String category2 = "Data 2";
//	    		String category3 = "Data 3";
//	    		String category4 = "Data 4";
//	    		String category5 = "Data 5";
//	    		// create the dataset...
//	    	//	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//	   
//	    		dataset.addValue(1000, series1, category1);
//	    		dataset.addValue(459, series1, category2);
//	    		dataset.addValue(356, series1, category3);
//	    		dataset.addValue(555, series1, category4);
//	    		dataset.addValue(1414, series1, category5);
	    		
	    		return dataset;
	    		}
		    }
	    

	


