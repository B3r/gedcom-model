Gedcom Model:
-
This is my own try to transfer the gedcom 5.5.1 specifications into a java model  
The source of the specification is
[here](http://homepages.rootsweb.com/~pmcbride/gedcom/55gcch2.htm)  
As a test I used the example at the end of that same page, however I modified it slightly, please compare the original file with my own updates

####Warning: This model is not a valid gedcom model yet! 
Use on your own responsibility  
Following elements are still in work (no guarantee on completeness of list): 

- Dates reduced to Gregorian Dates  
 If other dates needed as of now, implement `IDate`  
- `MultimediaRecord` not yet implemented

- File Types **_not_** restricted to  
[ bmp | gif | jpeg | ole | pcx | tiff | wav ]


Further I made some changes to following elements. Mainly because specifications were unclear: 
- `ordinanceProcess` flag in `SubmissionRecord` is mandatory
- ID in `AssociationStructure` is mandatory (different from specification)
- Class `SourceCitationNotable` exists due to an uncertainty in specification
- Example file uses Microfilm as MEDI type, which is not allowed in specification