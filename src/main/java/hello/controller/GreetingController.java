package hello.controller;

        import hello.model.Greeting;
        import hello.service.GreetingService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private GreetingService greetingService ;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @RequestMapping(value = "/greeting" , method = RequestMethod.POST,consumes = "application/json")
    public String upload(@RequestBody Greeting greeting) {

       //int id= greetingService.insert(greeting);
        return "welcome you ";
    }

    @RequestMapping(value = "/greeting/find/{name}",method = RequestMethod.GET,produces = "application/json")
    public String finByName(@PathVariable String name) {
        //return greetingService.findGreeting(name);
        return "bye bye";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }
}
