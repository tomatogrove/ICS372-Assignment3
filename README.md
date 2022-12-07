# ICS372-Assignment3

## Requirements and Use Cases
These files should be located within the *AdditionalFiles* folder

## Starting the Program
The Application file is called *CarDealerApplication*

On first startup, the program will ask for storage management permissions. If those are not allowed the app will not import/export files properly.

## Testing
All tests are located in the *edu.metrostate.cardealer(test)* directory with the resources folder containing testing files

## Importing Information
### JSON Files will generate a default value for
* Vehicle "unit" which is dollars
* Dealer "isRenting" which defaults to true
* Dealer "vehicleAcquisition" which defaults to true
* Dealer "name" which defaults to "Unknown"

### XML Files will generate a default value for
* Vehicle "vehicleAcquisitionDate" which is the date the file was submitted
* Dealer "isRenting" which defaults to true
* Dealer "vehicleAcquisition" which defaults to true
* Dealer "name" which defaults to "Unknown"

## Exporting Information
Exported JSON files may have a different format than entered and as such will not be re-imported properly. 

Additional fields include:
* Dealer name
* Dealer renting status
* Dealer vehicleAcquisitionStatus
* Vehicle rented status
* Vehicle currency unit

These exported files are located in the Download directory of the android device. 
