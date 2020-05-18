Gedcom Model:
-
This is my own try to transfer the gedcom 5.5.1 specifications into a java model  
##### Warning: This model is not a valid gedcom model yet!  
Use on your own responsibility and read this complete file for details!
Following elements are still in work (no guarantee on completeness of list): 
- Dates reduced to Gregorian Dates
- `MultimediaRecord` not yet implemented
- File Types **_not_** restricted to  
[ bmp | gif | jpeg | ole | pcx | tiff | wav ]
## Release Notes  

v0.1: Initial Implementation based on [this page](http://homepages.rootsweb.com/~pmcbride/gedcom/55gcch2.htm)  
v0.2: Updated the codebase according to specification pdf (see /specification/gedcom-5.5.1.pdf)  
v0.3: Added missing getters
v0.4: Added missing setters

## Test
As a test I used the example at the end of the specification pdf, however I modified it slightly.
Modifications of example file:  
- changed order
- changed some inconsistencies regarding capitalization
- changed some number formatting
- changed 'Microfilm' to 'film'

## How to use
The main class of this model is `GedcomModel`  
Dates reduced to Gregorian Dates for now  
If you need other dates, feel free to implement `IDate`

To use it as a maven dependency, download the release and
install it into the repository (replace capitalized words with your preferences):  
```
mvn install:install-file -Dfile=FILE_LOCATION -DgroupId=it.marvin_flock -DartifactId=gedcom-model -Dversion=VERSION -Dpackaging=jar -DgeneratePom=true
```
In your maven pom:
```        
           <dependency>
               <groupId>it.marvin_flock</groupId>
               <artifactId>gedcom-model</artifactId>
               <version>PUT_VERSION_HERE</version>
           </dependency>
```
