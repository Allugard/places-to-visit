var flag=0;
function initAutocomplete() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 50.0, lng: 30.0},
          zoom: 4
        });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var add = document.getElementById('add-marker');
        var options = {
          types: ['geocode']
        };
        var searchBox = new google.maps.places.SearchBox(input,options);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });
        var place;
        var places;

        var markers = [];


        searchBox.addListener('places_changed', function(event) {

          input.addEventListener('onchange', function(){
            event.preventDefault();
          });

          places = searchBox.getPlaces();
         
          if (places.length == 0) {
              return;
          }
          
          if (places.length > 1){
              places.shift();
          }      
          place = places[places.length-1]; 

          document.getElementById('clean-map').addEventListener('click', function(){
                    // Clear out the old markers.
                    if(document )
                    markers.forEach(function(marker) {
                      marker.setMap(null);
                    });
                    markers = [];
          });



          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          console.log(places);
          places.forEach(function(place1) {
            if (!place.geometry) {
              console.log("Returned place contains no geometry");
              return;
            }
              
              add.addEventListener('click', function(){
                var mark = place.geometry.location;
                  markers.push(new google.maps.Marker({
                      map: map,
                      title: place.name,
                      position: mark
                  }));               
              });             
            
            // Create a marker for each place.
            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          });
          map.fitBounds(bounds);

          console.log(markers);
        });
      }
