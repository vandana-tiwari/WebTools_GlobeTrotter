package com.webToolsProj.TripOrg.Controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webToolsProj.TripOrg.DAO.AdvertisementApartmentDAO;
import com.webToolsProj.TripOrg.DAO.BookingDAO;
import com.webToolsProj.TripOrg.DAO.CategoryDAO;
import com.webToolsProj.TripOrg.DAO.HostDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;
import com.webToolsProj.TripOrg.POJO.Category;
import com.webToolsProj.TripOrg.POJO.Host;



@Controller
@RequestMapping("/newAdvertisement.htm")
public class AddAdvertAptFormController {

	@Autowired
	@Qualifier("advertAptValidator")
	AdvertAptValidator advertAptValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(advertAptValidator);
	}
	
	@Autowired
	@Qualifier("advertAptDAO")
	AdvertisementApartmentDAO advertAptDAO;
	
	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;
	
	@Autowired
	@Qualifier("categoryDAO")
	CategoryDAO categoryDAO;
	
	@Autowired
	@Qualifier("hostDAO")
	HostDAO hostDAO;
	
	
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("advertApt")AdvertisementApartment advertApt,@RequestParam("photo") MultipartFile photoFile,HttpServletRequest request,BindingResult result) throws Exception{

		advertAptValidator.validate(advertApt, result);
		if(result.hasErrors())
		{
			return "addAppartmentForm";
		}
    	
		advertApt.setPhoto(photoFile);
        String username = advertApt.getPostedBy();   //get posting user from addAdvertForm
        String username1 = request.getParameter(advertApt.getPostedBy());
        System.out.println("Username::::::"+username+username1);
        String categoryTitle = advertApt.getCategory_name();   //get category user from addAdvertForm
        
        String categoryTitle1 = request.getParameter(advertApt.getCategory_name());
        System.out.println("categoryTitle::::::"+categoryTitle+categoryTitle1);
        String title = advertApt.getTitle();      //get advert title user from addAdvertForm
        String message = advertApt.getMessage();    //get user message from addAdvertForm
        System.out.println("message::::::"+message);
        float price = advertApt.getPrice();
        String street = advertApt.getAddress().getStreet();
        String city = advertApt.getAddress().getCity();
        String state = advertApt.getAddress().getState();
        int zip = advertApt.getAddress().getZip();
        int rooms = advertApt.getRooms();
        int numOfBeds = advertApt.getNumOfBeds();
        float bathrooms = advertApt.getBathrooms();
        int maxOccupants = advertApt.getMaxOccupants();
        float priceExtraOccupant = advertApt.getPriceExtraOccupant();
        String furnished = advertApt.getFurnished();
        Date checkIn = advertApt.getCheckIn();
        Date checkOut = advertApt.getCheckOut();
        //String photo = advertApt.getPhoto();
        
        try {

            System.out.println("searhing from database");
            //searching from database
            Host host = hostDAO.get(username);
            if(categoryDAO.get(categoryTitle) == null){
            	System.out.println("Creating............");
            	categoryDAO.create("Apartment");
            }
            System.out.println("searching from database");
            //searching from database
            Category category = categoryDAO.get(categoryTitle);

            System.out.println("insert a new advert");
            //insert a new advert
            File file;
            System.out.println("111111111111111111");
            String check = File.separator; //Checking if system is linux based or windows based by checking seprator used.
            System.out.println("22222222222");
            String path = null;
            if(check.equalsIgnoreCase("\\")) {
            	System.out.println("33333333333333");
             path = servletContext.getRealPath("").replace("build\\",""); //Netbeans projects gives real path as Lab6/build/web/ so we need to replace build in the path.
         }
         
             if(check.equalsIgnoreCase("/")) {
            	 System.out.println("44444444444444444");
            path = servletContext.getRealPath("").replace("build/","");
            System.out.println("5555555555555");
            path +=  "/resources/images/";  //Adding trailing slash for Mac systems.

         }
             path+="resources\\images\\";
             System.out.println("6666666666666");
             if(advertApt.getPhoto() != null){
            	 System.out.println("77777777777"+advertApt.getPhoto());
                String fileNameWithExt = System.currentTimeMillis() + photoFile.getOriginalFilename();

                 file = new File(path+fileNameWithExt);
                 System.out.println("99999999999"+file);
                 String context = servletContext.getContextPath();
                 System.out.println("0000000000000000"+context);
                 photoFile.transferTo(file);
                 System.out.println("0000000000000000"+path+fileNameWithExt);
                 //advertApt.setPhotoName(path+fileNameWithExt);
                 String photoFileName= context + "/resources/images/" +fileNameWithExt;
                 advertApt.setPhotoName(photoFileName);
                 System.out.println("0000000000000000");
             }
            
            System.out.println("title"+title);
            System.out.println("advertApt.getPhotoName()"+advertApt.getPhotoName());
            System.out.println("message"+message);
            System.out.println("host"+host);
            System.out.println("category.getId()"+category.getId());
            System.out.println("category.getTitle()"+category.getTitle());
            System.out.println("price"+price);
            System.out.println("street"+street);
            System.out.println("city"+city);
            System.out.println("state"+state);
            System.out.println("zip"+zip);
            System.out.println("rooms"+rooms);
            System.out.println("numOfBeds"+numOfBeds);
            System.out.println("bathrooms"+bathrooms);
            System.out.println("maxOccupants"+maxOccupants);
            System.out.println("priceExtraOccupant"+priceExtraOccupant);
            System.out.println("furnished"+furnished);
            System.out.println("checkIn"+checkIn);
            System.out.println("checkIn"+checkOut);
            
            AdvertisementApartment advApt = advertAptDAO.create(title,advertApt.getPhotoName(), message, host,category.getId(),category.getTitle(),price, street, city, state, zip, rooms, numOfBeds,bathrooms,maxOccupants,priceExtraOccupant,furnished,checkIn,checkOut);

            System.out.println("addAdvert");
            category.addAdvertisement(advApt);
            System.out.println("category");
            categoryDAO.save(category);

            
        } catch (AdException e) {
            System.out.println(e.getMessage());
        }
        return "home";
    }
    
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("advertApt")AdvertisementApartment advertApt, BindingResult result) { 
   
        return "addAppartmentForm"; 
    } 
}