# OjaiClient

The quickest and easiest way to add Bluetooth barcode scanning support
to your Android application.

>**WARNING**: This is an *alpha* version. Expect breaking changes

## Example

    import com.nomadlabs.ojai.client.ScanActivity;

    ...

    public class MainActivity extends ScanActivity {
    
        ...
        
        @Override
        public void onScan(String data, Bundle extras) {
            // Do something
        }
    }

## Installation

Do not clone directly into your eclipse workspace, it will make puppies
cry. You will get the option to copy the project into your workspace 
when you import it into eclipse.

    git clone git@github.com:nomad-labs/OjaiClient.git 

Add the Ojai Client library to eclipse. 

In eclipse, `File` > `New` > `Project...` > `Android Project from
Exisiting Code`. Set "Root Directory" to the location of your clone.
"Copy projects into workspace" is optional; If you opt to copy, delete
the original clone.

## Usage

In the new or existing Android application to which you'd like to add a
ScanActivity, add OjaiClient as a dependency.

`Project` > `Properties` > `Android`. Click `Add...` in the "Library" 
fieldset. Select "OjaiClient".

Create a new or modify and existing activity by importing ScanActivity 
and overriding the onScan method.

### Example

[OjaiWorld](https://github.com/nomad-labs/OjaiWorld) is a simple sample client application and the source is available on GitHub.

## To Do

### Client-side

* **Intent Integrator** - OjaiClient applications depend on
[Barcode Scanner Pro](https://play.google.com/store/apps/details?id=com.nomadlabs.ojai). If Barcode Scanner Pro is not installed, binding to the service
fails silently. An Intent Integrator will catch that failure and prompt
the user to install Barcode Scanner Pro from their respective app store.
* **Scanner Configuration** - Allow the application to configure the
scanner: Enable/disable symbologies, set confirmation mode and action,
query scanner properties and battery level, etc.

### Server-side

* **Scanner Connection Wizard 1D Support** - Add support to the Scanner Connection Wizard for 1D scanners.

## License

Copyright 2013 Enrico Mills

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
