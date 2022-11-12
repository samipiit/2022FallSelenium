package data_providers;

import base.Base;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders extends Base {


    @DataProvider(name = "testSelectItem")
    public Object[][] getDataSelectItem() throws IOException {
        return excel.readStringArrays(testDataFilePath, "TestProductsDP");
    }

}
