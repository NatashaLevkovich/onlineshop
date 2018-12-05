package entities;

public enum ProductSubcategory {
    GIRLS,
    BOYS,
    NEWBORN,
    UNDERWEAR;

    public static ProductSubcategory getSubcategory(String category){
        for (ProductSubcategory subcategory : ProductSubcategory.values()){
            if (category.equalsIgnoreCase(subcategory.toString())){
                return subcategory;
            }
        }
        return BOYS;
    }
}
