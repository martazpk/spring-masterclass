package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductWebController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("add-product.html")
    ModelAndView addProduct(){
        ModelAndView modelAndView = new ModelAndView("add-product");
        modelAndView.addObject(new ProductTransferObject());
        return modelAndView;
    }

    @PostMapping("add-product.html")
    String saveProduct(@Valid ProductTransferObject productTransferObject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-product";
        }
        Product product = productMapper.toProduct(productTransferObject);
        productService.add(product);
        return "redirect:show-products.html";
    }

    @GetMapping("show-products.html")
    ModelAndView showUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        PagedResult<Product> usersPage = productService.getAll(pageNumber, pageSize);
        PagedResultTransferObject<ProductTransferObject> productsPageTO = productMapper.toProductTransferObjectsPage(usersPage);
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", productsPageTO);
        return modelAndView;
    }
}
