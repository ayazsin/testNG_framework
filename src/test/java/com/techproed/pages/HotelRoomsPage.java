package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelRoomsPage {

    public HotelRoomsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(partialLinkText = "ADD HOTELROOM")
    public WebElement addHotelRoomLink;

    @FindBy(id = "IDHotel")
    public WebElement idDropdown;

    @FindBy(id="Code")
    public WebElement code;

    @FindBy(id = "Name")
    public WebElement name;

    @FindBy(id = "Location")
    public WebElement location;

    ////*[@id="cke_1_contents"]/textarea
    @FindBy(xpath = "//textarea[@dir = 'ltr']")
    public WebElement description;

    @FindBy(id = "Price")
    public WebElement price;

    @FindBy(xpath = "//li[@data-id='700']")
    public WebElement price700;

    //IDGroupRoomType
    @FindBy(id = "IDGroupRoomType")
    public WebElement roomType;

    //MaxAdultCount
    @FindBy(id = "MaxAdultCount")
    public WebElement maxAdultCount;

    //MaxChildCount
    @FindBy(id = "MaxChildCount")
    public WebElement maxChildCount;

    //IsAvailable
    @FindBy(id = "IsAvailable")
    public WebElement approved;

    //btnSubmit
    @FindBy(id = "btnSubmit")
    public WebElement save;

    @FindBy(xpath = "//div[@class='bootbox-body']")
    public WebElement message;

    @FindBy(xpath = "//button[@data-bb-handler='ok']")
    public WebElement ok;























    //LOCATE ALL OBJECTS IN THIS CLASS
    //AND
}
