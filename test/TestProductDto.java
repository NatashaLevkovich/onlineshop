import DAO.Dto.ProductDtoDao;
import entities.Dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class TestProductDto {
    private ProductDtoDao productDtoDao = ProductDtoDao.getInstance();

    @Test
    public void test() throws SQLException {
        List<ProductDto> list = productDtoDao.get(9L);
        Assert.assertEquals(4, list.size(), 0.00);
    }
}

