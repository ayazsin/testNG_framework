package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HotelListPage {

    public HotelListPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "menuHotels")
    public WebElement hotelManagement;

    @FindBy(partialLinkText = "Hotel List")
    public WebElement hotelList;

    @FindBy(id = "lkpGroups")
    public WebElement idGroup;

    @FindBy(xpath = "//button[@class='btn btn-sm yellow filter-submit margin-bottom']")
    public WebElement search;

    @FindBy(xpath = "//button[@class='btn btn-sm red filter-cancel']")
    public WebElement clear;

    @FindBy(xpath = "//h3[@class='page-title']")
    public WebElement currentDate;

    @FindBy(xpath = "(//select[@name='datatable_ajax_length'])[2]")
    public WebElement records;

    @FindBy(xpath = "//a[@class='btn btn-sm default next']")
    public WebElement next;

    @FindBy(xpath = "(//input[@class='pagination-panel-input form-control input-mini input-inline input-sm'])[1]")
    public WebElement currentPage;

    @FindBy(id = "btnExportTable")
    public WebElement export;

    @FindBy(xpath = "(//a[@class='btn btn-xs default'])[1]")
    public WebElement firstDetail;

    @FindBy(xpath = "(//div[@class='caption'])[1]")
    public WebElement captionEdit;

    @FindBy(id = "Code")
    public WebElement code;

    @FindBy(xpath = "(//div[@class='bootbox-body'])[1]")
    public WebElement successMessage;

    @FindBy(xpath = "//button[@class='btn green']")
    public WebElement save;

    @FindBy(xpath = "//button[@data-bb-handler='ok']")
    public WebElement ok;

    @FindBy(linkText = "Properties")
    public WebElement propertiesTab;

    @FindBy(id = "product_barcodeCode_101")
    public WebElement barcode;

    @FindBy(xpath = "//a[@class='btn default btn-sm']")
    public WebElement update;

    @FindBy(xpath = "//div[@class='bootbox-body']")
    public WebElement valueUpdatedMessage;

    @FindBy(id = "btnDelete")
    public WebElement delete;

    @FindBy(xpath = "//button[@data-bb-handler='confirm']")
    public WebElement confirm;

    @FindBy(xpath = "//div[@class='bootbox-body']")
    public WebElement errorMessage;






}
