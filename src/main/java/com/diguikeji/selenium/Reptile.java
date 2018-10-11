package com.diguikeji.selenium;

import com.diguikeji.Dao.ProductDao;
import com.diguikeji.entity.Product;
import com.sun.javafx.collections.MappingChange;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reptile {

    public void reptile() throws InterruptedException, IOException {

        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://kns.cnki.net/kns/brief/result.aspx?dbPrefix=CCND");
        webDriver.manage().window().maximize();
        //选择检索条件(全文)
        WebElement select = webDriver.findElement(By.xpath("//*[@id=\"txt_1_sel\"]/option[4]"));
        select.click();
        //选择需要查询的六家报纸
        WebElement input2 = webDriver.findElement(By.xpath("//*[@id=\"magazine_value1\"]"));
        input2.sendKeys("中国证券报+上海证券报+证券时报+经济观察报+中国经营报+21世纪经济报道");



        LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) MapCompany.company();

        for (HashMap.Entry<String, String> entry : map.entrySet()) {


            //选择查询的公司名称
            WebElement input = webDriver.findElement(By.xpath("//*[@id=\"txt_1_value1\"]"));
            input.sendKeys(entry.getValue());

            //2013年
            String numThree = this.datenum(webDriver, "2013-1-1", "2013-12-31");
            //插入2013年的数据
            System.out.println(entry.getValue() + "2013年：" + numThree + "条结果");
            //返回
            this.back(webDriver);

            //2014年
            String numFour = this.datenum(webDriver, "2014-1-1", "2014-12-31");
            //插入2014年的数据
            System.out.println(entry.getValue() + "2014年：" + numFour + "条结果");
            //返回
            this.back(webDriver);

            //2015年
            String numFive = this.datenum(webDriver, "2015-1-1", "2015-12-31");
            //插入2015年的数据
            System.out.println(entry.getValue() + "2015年：" + numFive + "条结果");
            //返回
            this.back(webDriver);

            //2016年
            String numSix = this.datenum(webDriver, "2016-1-1", "2016-12-31");
            //插入2016年的数据
            System.out.println(entry.getValue() + "2016年：" + numSix + "条结果");
            //返回
            this.back(webDriver);

            //2017年
            String numSeven = this.datenum(webDriver, "2017-1-1", "2017-12-31");
            //插入2017年的数据
            System.out.println(entry.getValue() + "2017年：" + numSeven + "条结果");
            //返回
            this.back(webDriver);

            //2018年
            String numEight = this.datenum(webDriver, "2018-1-1", "2018-12-31");
            //插入2017年的数据
            System.out.println(entry.getValue() + "2018年：" + numEight + "条结果");
            //返回
            this.back(webDriver);

            ProductDao productDao = new ProductDao();
            Product product = new Product(entry.getKey(),entry.getValue(),Integer.parseInt(numThree),
                    Integer.parseInt(numFour),Integer.parseInt(numFive),Integer.parseInt(numSix),Integer.parseInt(numSeven),Integer.parseInt(numEight));
            Product product1 = productDao.addProduct(product);
            System.out.println(product1+"插入数据库成功");

            this.rollText(webDriver);
        }
    }

    //选择年限返回结果
    private String datenum(WebDriver webDriver, String startDate, String endDate) throws InterruptedException {
        WebElement start = webDriver.findElement(By.xpath("//*[@id=\"publishdate_from\"]"));
        start.sendKeys(startDate);
        WebElement end = webDriver.findElement(By.xpath("//*[@id=\"publishdate_to\"]"));
        end.sendKeys(endDate);
        String num = this.SelectNum(webDriver);
        return num;
    }

    private void back(WebDriver webDriver) throws InterruptedException {
        this.rollBack(webDriver);
        this.dateStartRemove(webDriver);
        this.dateendRemove(webDriver);
    }


    //清除开始日期
    private void dateStartRemove(WebDriver webDriver) {
        WebElement dateRemove = webDriver.findElement(By.xpath("//*[@id=\"publishdate_from\"]"));
        dateRemove.clear();
    }

    //清除结束日期
    private void dateendRemove(WebDriver webDriver) {
        WebElement dateRemove = webDriver.findElement(By.xpath("//*[@id=\"publishdate_to\"]"));
        dateRemove.clear();
    }

    //点击查询到返回条数
    public String SelectNum(WebDriver webDriver) throws InterruptedException {
        //点击查询
        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"btnSearch\"]"));
        button.click();
        Thread.sleep(5000);
        //切换frame
        webDriver.switchTo().frame("iframeResult");
        WebElement num = webDriver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div"));
        //System.out.println(num.getText());
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(num.getText());
        String group = "";
        while (matcher.find()) {
            group = matcher.group(0);
        }
        return group;
    }

    //切换到iframe
    private void rollBack(WebDriver webDriver) throws InterruptedException {
        //切回iframe
        webDriver.switchTo().defaultContent();
        Thread.sleep(2000);

    }

    //切回开始状态
    private void rollText(WebDriver webDriver) {
        //清空文本框
        WebElement inputRemove = webDriver.findElement(By.xpath("//*[@id=\"txt_1_value1\"]"));
        inputRemove.clear();
    }

}





