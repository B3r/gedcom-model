Gedcom Model:
-
This is my own try to transfer the gedcom 5.5.1 specifications into a java model  
##### Warning: This model is not a valid gedcom model yet!  
Use on your own responsibility and read this complete file for details!
Following elements are still in work (no guarantee on completeness of list): 
- Dates are reduced to Gregorian Dates
- `MultimediaRecord` not yet implemented
- File Types **_not_** restricted to  
[ bmp | gif | jpeg | ole | pcx | tiff | wav ]
## Release Notes  

v0.1: Initial Implementation based on [this page](http://homepages.rootsweb.com/~pmcbride/gedcom/55gcch2.htm)  
v0.2: Updated the codebase according to specification pdf (see /specification/gedcom-5.5.1.pdf)

## Test
As a test I used the example at the end of the specification pdf, however I modified it slightly.
Modifications of example file:  
- changed order
- changed some inconsistencies regarding capitalization
- changed some number formatting
- changed 'Microfilm' to 'film'

## How to use
The main class of this model is `GedcomModel`
Dates are reduced to Gregorian Dates for now  
If other dates are needed, feel free to implement `IDate`
