/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsearch;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Yago
 */
public class GSearch {

    /**
     * @param args the command line arguments
     */
    static WebDriver driver;
    static DesiredCapabilities capabilities;
    static Document doc;
    final static long startTime = System.nanoTime();
    public static int nn, input, na;

    public static void selUp(String url, boolean online) {
        if (!online) {
            url = "file:///" + url;
        }
        try {
            // Método para levantar selenium
            //String folder = SeleniumSoup1.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"g.exe";//GECKODRIVER.exe
            String folder = System.getProperty("user.dir") + "/driver/geckodriver.exe";//GECKODRIVER.exe
            //System.setProperty("webdriver.gecko.driver", "C:/p/geckodriver-v0.15.0-win64/geckodriver.exe");
            //folder=folder.substring(1, folder.length());
            System.out.println("[INFO]: firefox driver at " + folder);
            
            System.setProperty("webdriver.gecko.driver", folder);
            
            driver = new FirefoxDriver();
            
            capabilities = DesiredCapabilities.htmlUnit();
            
            capabilities.setBrowserName("FireFrog");
           
            //driver.manage().timeouts().pageLoadTimeout(100000, TimeUnit.MILLISECONDS);
            driver.manage().window().maximize();
            driver.get(url);
            
            System.out.println("[INFO]: selenium got " + url);
        } catch (Exception ex) {
            System.out.println("[INFO]: selenium PROBLEM CAN NOT GET UP");
            ex.printStackTrace();

        }
    }

    public static void mein(int n) {
        String url = "https://www.google.es/";
        System.out.println("[INFO]: new reading from " + System.getProperty("user.dir") + "/search.txt");
        File file = new File(System.getProperty("user.dir") + "/search.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                driver.get(url);
                driver.findElement(By.id("lst-ib")).sendKeys(line);
                driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
                Thread.sleep(n);
            }
        } catch (IOException ex) {
            Logger.getLogger(GSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void mbin(){
        System.out.println("   _____  _____                     _     \n" +
                            "  / ____|/ ____|                   | |    \n" +
                            " | |  __| (___   ___  __ _ _ __ ___| |__  \n" +
                            " | | |_ |\\___ \\ / _ \\/ _` | '__/ __| '_ \\ \n" +
                            " | |__| |____) |  __/ (_| | | | (__| | | |\n" +
                            "  \\_____|_____/ \\___|\\__,_|_|  \\___|_| |_|\n" +
                            "                                          \n" +
                            "                                          \n" +
                            "");
        
        
        System.out.println("Edit search.txt file at "+System.getProperty("user.dir")+" for customizing.");
        System.out.println("");
        int n;
        String et="";
        boolean eternal = false;
        Scanner scan=new Scanner(System.in);
        
            System.out.print("Write time in ms between searchs and press enter: ");
             n=Integer.parseInt(scan.nextLine());
            System.out.print("Do you want the script to stop? Y/N: ");
             et=scan.nextLine();
             if(et.equalsIgnoreCase("n")){
                 eternal=true;
             }
        
        try {
            String url = "https://www.google.es/";
            selUp(url, true);
            //soupUp(url, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("[ERROR]: URL NO VÁLIDA / SIN CONEXIÓN");
        }
        
        if (eternal) {
            while (eternal) {
                mein(n); 
            }
        } else {
            mein(n);
        }
    }

    public static void main(String[] args) {
        mbin();
        driver.close();
        driver.quit();
    }

}
