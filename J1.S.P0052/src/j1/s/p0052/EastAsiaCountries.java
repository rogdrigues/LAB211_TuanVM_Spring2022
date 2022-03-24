/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0052;

/**
 *
 * @author phong
 */
public class EastAsiaCountries extends Country implements Comparable<EastAsiaCountries>{

    private String countryTerrain;

    public EastAsiaCountries() {
    }

    public EastAsiaCountries(String countryCode, String countryName,
            float totalArea, String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    }

    @Override
    public void Display() {
        super.Display();
        System.out.printf("%-20s\n", countryTerrain);
        System.out.println();
    }
    @Override
    public int compareTo(EastAsiaCountries country) {
        return this.getCountryName().compareToIgnoreCase(country.getCountryName());
    }
}
