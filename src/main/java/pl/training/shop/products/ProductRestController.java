package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<ProductTransferObject> add(@RequestBody @Valid ProductTransferObject transferObject) {
        Product product = productMapper.toProduct(transferObject);
        Long id = productService.add(product).getId();
        URI uriLocation = uriBuilder.requestUriWithId(id);
        return ResponseEntity.created(uriLocation).build();
    }

    @GetMapping({"{id}"})
    public ResponseEntity<ProductTransferObject> getById(@PathVariable Long id) {
        Product byId = productService.findById(id);
        ProductTransferObject productTransferObject = productMapper.toProductTransferObject(byId);
        return ResponseEntity.ok(productTransferObject);
    }

    @GetMapping
    PagedResultTransferObject<ProductTransferObject> getByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        PagedResult<Product> byNamePage = productService.findByName(name, pageNumber, pageSize);
        return productMapper.toProductTransferObjectsPage(byNamePage);
    }

    @GetMapping("all")
    PagedResultTransferObject<ProductTransferObject> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        PagedResult<Product> byNamePage = productService.getAll(pageNumber, pageSize);
        return productMapper.toProductTransferObjectsPage(byNamePage);
    }

    @PostMapping("{id}/files")
    String submit(@PathVariable Long id, @RequestParam MultipartFile file){
        return "File " + file.getOriginalFilename() + " uploaded.";
    }
}
