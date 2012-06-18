        $(document).ready(function () {

        
          $('form[name="frameForm"]').validate( {
            rules: {
              'identifier': {
                required: true
              },
              'materialId': {
                required: true
              },
              'rimType': {
                required: true
              },
              'gender': {
                required: true
              },
              'rimShapeId': {
                required: true
              },
              'endpieceHeight': {
                required: true
              },
              'use': {
                required: true
              },
              'fullyPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'semiPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'standardBasePrice': {
                range: [0.0,2000.0]
              },
              'fullyPersonalisedProductionTime': {
                range: [0,30]
              },
              'semiPersonalisedProductionTime': {
                range: [0,30]
              }

            }
          });
                    
          // Frontal options popup
          var frontalOptions = $('#frontalOptionsPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_frontal_options_popup_title'],
              closeOnEscape: true,
              modal: true
          });
            
          $('#frontalOptionsButton').click(function() {
            frontalOptions.dialog('open');
            return false;
          });
          
          // Frontal popup
          var frontalConfig = $('#frontalConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_frontal_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                  
                var validData = validateAllFrontalTempleConfigVals();
                if ( !validData ) {
                    alert("Invalid configuration data specified of required value missing. Please check all data before continuing"); // TODO - change to a message lookup
                    return false;
                }
                  
                // Copy various fields from the popup into hidden fields in the form
                $('#finishingAspect').val($('#popupFinishingAspect').val());
                $('#mattePrice').val($('#popupMattePrice').val());
                $('#matteProductionTime').val($('#popupMatteProductionTime').val());
                $('#brilliantPrice').val($('#popupBrilliantPrice').val());
                $('#brilliantProductionTime').val($('#popupBrilliantProductionTime').val());
                $('#minLensThickness').val($('#popupMinLensThickness').val());
                $('#leftTopPupilFrameDistance').val($('#popupLeftTopPupilFrameDistance').val());
                $('#leftBottomPupilFrameDistancePSV').val($('#popupLeftBottomPupilFrameDistancePSV').val());
                $('#leftBottomPupilFrameDistanceMF').val($('#popupLeftBottomPupilFrameDistanceMF').val());
                $('#rightTopPupilFrameDistance').val($('#popupRightTopPupilFrameDistance').val());
                $('#rightBottomPupilFrameDistancePSV').val($('#popupRightBottomPupilFrameDistancePSV').val());
                $('#rightBottomPupilFrameDistanceMF').val($('#popupRightBottomPupilFrameDistanceMF').val());
              }
          });
            
          $('#frontalPopupButton').click(function() {
            frontalConfig.dialog('open');
            return false;
          });

          // Frontal dimensions popup
          var frontalDimsConfig = $('#frontalDimsConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_frontal_dimensions_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 600,
              beforeClose: function(event, ui) { 
                  
                var fieldsValid = validateAllFrontalDimensionVals();
                if (!fieldsValid ) {
                    alert("Invalid measurement(s) specified. Please check all values before continuing."); // TODO - make this a message lookup...
                    return false;
                }
                    
                // Copy various fields from the popup into hidden fields in the form
                $('#boxingHorizontalStandard').val($('#popupBoxingHorizontalStandard').val());
                $('#boxingHorizontalMin').val($('#popupBoxingHorizontalMin').val());
                $('#boxingHorizontalMax').val($('#popupBoxingHorizontalMax').val());
                $('#boxingVerticalStandard').val($('#popupBoxingVerticalStandard').val());
                $('#boxingVerticalMin').val($('#popupBoxingVerticalMin').val());
                $('#boxingVerticalMax').val($('#popupBoxingVerticalMax').val());
                $('#pantoscopicAngleLeftStandard').val($('#popupPantoscopicAngleLeftStandard').val());
                $('#pantoscopicAngleLeftMin').val($('#popupPantoscopicAngleLeftMin').val());
                $('#pantoscopicAngleLeftMax').val($('#popupPantoscopicAngleLeftMax').val());
                $('#pantoscopicAngleRightStandard').val($('#popupPantoscopicAngleRightStandard').val());
                $('#pantoscopicAngleRightMin').val($('#popupPantoscopicAngleRightMin').val());
                $('#pantoscopicAngleRightMax').val($('#popupPantoscopicAngleRightMax').val());
                $('#bridgeWidthStandard').val($('#popupBridgeWidthStandard').val());
                $('#bridgeWidthMin').val($('#popupBridgeWidthMin').val());
                $('#bridgeWidthMax').val($('#popupBridgeWidthMax').val());
                $('#bridgeHeightStandard').val($('#popupBridgeHeightStandard').val());
                $('#bridgeHeightMin').val($('#popupBridgeHeightMin').val());
                $('#bridgeHeightMax').val($('#popupBridgeHeightMax').val());
                $('#heelWidthStandard').val($('#popupHeelWidthStandard').val());
                $('#heelWidthMin').val($('#popupHeelWidthMin').val());
                $('#heelWidthMax').val($('#popupHeelWidthMax').val());
                $('#internalRimReductionStandard').val($('#popupInternalRimReductionStandard').val());
                $('#internalRimReductionMin').val($('#popupInternalRimReductionMin').val());
                $('#internalRimReductionMax').val($('#popupInternalRimReductionMax').val());
                $('#baseOfFrameStandard').val($('#popupBaseOfFrameStandard').val());
                $('#baseOfFrameMin').val($('#popupBaseOfFrameMin').val());
                $('#baseOfFrameMax').val($('#popupBaseOfFrameMax').val());
                $('#wrapAngleStandard').val($('#popupWrapAngleStandard').val());
                $('#wrapAngleMin').val($('#popupWrapAngleMin').val());
                $('#wrapAngleMax').val($('#popupWrapAngleMax').val());
                $('#totalLengthStandard').val($('#popupTotalLengthStandard').val());
                
              }

          });
          
          $('#frontalDimsPopupButton').click(function() {
            frontalDimsConfig.dialog('open');
            return false;
          });
          
          // Frontal total dimensions popup
          var frontalTotalSizeConfig = $('#totalSizeConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_total_size_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 600,
              beforeClose: function(event, ui) { 
                  
                var totalSizesValid = validateAllTotalSizeVals();
                if ( !totalSizesValid ) {
                    alert("Invalid dimension specified. Please check all values before continuing."); // TODO - make this a message lookup
                    return false;
                }
                
                // Copy various fields from the popup into hidden fields in the form
                $('#totalSizeBridgeWidth1').val($('#popupTotalSizeBridgeWidth1').val());
                $('#totalSizeMin1').val($('#popupTotalSizeMin1').val());
                $('#totalSizeMax1').val($('#popupTotalSizeMax1').val());
                $('#totalSizeBridgeWidth2').val($('#popupTotalSizeBridgeWidth2').val());
                $('#totalSizeMin2').val($('#popupTotalSizeMin2').val());
                $('#totalSizeMax2').val($('#popupTotalSizeMax2').val());
                $('#totalSizeBridgeWidth3').val($('#popupTotalSizeBridgeWidth3').val());
                $('#totalSizeMin3').val($('#popupTotalSizeMin3').val());
                $('#totalSizeMax3').val($('#popupTotalSizeMax3').val());
                $('#totalSizeBridgeWidth4').val($('#popupTotalSizeBridgeWidth4').val());
                $('#totalSizeMin4').val($('#popupTotalSizeMin4').val());
                $('#totalSizeMax4').val($('#popupTotalSizeMax4').val());
                $('#totalSizeBridgeWidth5').val($('#popupTotalSizeBridgeWidth5').val());
                $('#totalSizeMin5').val($('#popupTotalSizeMin5').val());
                $('#totalSizeMax5').val($('#popupTotalSizeMax5').val());
                $('#totalSizeBridgeWidth6').val($('#popupTotalSizeBridgeWidth6').val());
                $('#totalSizeMin6').val($('#popupTotalSizeMin6').val());
                $('#totalSizeMax6').val($('#popupTotalSizeMax6').val());
                $('#totalSizeBridgeWidth7').val($('#popupTotalSizeBridgeWidth7').val());
                $('#totalSizeMin7').val($('#popupTotalSizeMin7').val());
                $('#totalSizeMax7').val($('#popupTotalSizeMax7').val());
                $('#totalSizeBridgeWidth8').val($('#popupTotalSizeBridgeWidth8').val());
                $('#totalSizeMin8').val($('#popupTotalSizeMin8').val());
                $('#totalSizeMax8').val($('#popupTotalSizeMax8').val());
                $('#totalSizeBridgeWidth9').val($('#popupTotalSizeBridgeWidth9').val());
                $('#totalSizeMin9').val($('#popupTotalSizeMin9').val());
                $('#totalSizeMax9').val($('#popupTotalSizeMax9').val());
                $('#totalSizeBridgeWidth10').val($('#popupTotalSizeBridgeWidth10').val());
                $('#totalSizeMin10').val($('#popupTotalSizeMin10').val());
                $('#totalSizeMax10').val($('#popupTotalSizeMax10').val());
                $('#totalSizeBridgeWidth11').val($('#popupTotalSizeBridgeWidth11').val());
                $('#totalSizeMin11').val($('#popupTotalSizeMin11').val());
                $('#totalSizeMax11').val($('#popupTotalSizeMax11').val());
                $('#totalSizeBridgeWidth12').val($('#popupTotalSizeBridgeWidth12').val());
                $('#totalSizeMin12').val($('#popupTotalSizeMin12').val());
                $('#totalSizeMax12').val($('#popupTotalSizeMax12').val());
                $('#totalSizeBridgeWidth13').val($('#popupTotalSizeBridgeWidth13').val());
                $('#totalSizeMin13').val($('#popupTotalSizeMin13').val());
                $('#totalSizeMax13').val($('#popupTotalSizeMax13').val());
                $('#totalSizeBridgeWidth14').val($('#popupTotalSizeBridgeWidth14').val());
                $('#totalSizeMin14').val($('#popupTotalSizeMin14').val());
                $('#totalSizeMax14').val($('#popupTotalSizeMax14').val());
                $('#totalSizeBridgeWidth15').val($('#popupTotalSizeBridgeWidth15').val());
                $('#totalSizeMin15').val($('#popupTotalSizeMin15').val());
                $('#totalSizeMax15').val($('#popupTotalSizeMax15').val());
              }

          });
          
          $('#totalSizePopupButton').click(function() {
            frontalTotalSizeConfig.dialog('open');
            return false;
          });

          // Frontal decoration popup
          var frontalDecorationConfig = $('#frontalDecorationConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_frontal_decoration_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                  
                // Check any added fields are valid before continuing
                var fieldsValid = validateAllFrontalNewTextureVals();
                if ( !fieldsValid ) {
                    alert("Invalid decoration value specified or values missing. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }

                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupFrontalBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#frontalNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupFrontalBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#frontalRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='frontalTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#frontalNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='frontalTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#frontalNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='frontalTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#frontalNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='frontalTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#frontalNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='frontalTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#frontalNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='frontalTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#frontalNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='frontalTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#frontalNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupFrontalTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#frontalTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#frontalDecorationPopupButton').click(function() {
            frontalDecorationConfig.dialog('open');
            return false;
          });


          // Left temple options popup
          var leftTempleOptions = $('#leftTempleOptionsPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_left_temple_options_popup_title'],
              closeOnEscape: true,
              modal: true
          });
            
          $('#leftTempleOptionsButton').click(function() {
            leftTempleOptions.dialog('open');
            return false;
          });

          // Left temple popup
          var leftTempleConfig = $('#leftTempleConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_left_temple_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                  
                var configValid = validateAllLeftTempleConfigVals();
                if ( !configValid ) {
                    alert("Invalid configuration value specified. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }
                
                // Copy various fields from the popup into hidden fields in the form
                $('#leftTempleFinishingAspect').val($('#popupLeftTempleFinishingAspect').val());
                $('#leftTempleMattePrice').val($('#popupLeftTempleMattePrice').val());
                $('#leftTempleMatteProductionTime').val($('#popupLeftTempleMatteProductionTime').val());
                $('#leftTempleBrilliantPrice').val($('#popupLeftTempleBrilliantPrice').val());
                $('#leftTempleBrilliantProductionTime').val($('#popupLeftTempleBrilliantProductionTime').val());
              }
          });
          
          $('#leftTemplePopupButton').click(function() {
            leftTempleConfig.dialog('open');
            return false;
          });
          
          // Left temple dimensions popup
          var leftTempleDimsConfig = $('#leftTempleDimsPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_left_temple_dimensions_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 650,
              beforeClose: function(event, ui) { 
                  
                var dataValid = validateAllLeftTempleDimensionVals();
                if ( !dataValid ) {
                    alert("Invalid dimension value specified. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }
                
                // Copy various fields from the popup into hidden fields in the form
                $('#leftTempleLengthStandard').val($('#popupLeftTempleLengthStandard').val());
                $('#leftTempleLengthMin').val($('#popupLeftTempleLengthMin').val());
                $('#leftTempleLengthMax').val($('#popupLeftTempleLengthMax').val());
                $('#leftTempleOpeningAngleStandard').val($('#popupLeftTempleOpeningAngleStandard').val());
                $('#leftTempleOpeningAngleMin').val($('#popupLeftTempleOpeningAngleMin').val());
                $('#leftTempleOpeningAngleMax').val($('#popupLeftTempleOpeningAngleMax').val());
                $('#leftTempleMainCurvatureStandard').val($('#popupLeftTempleMainCurvatureStandard').val());
                $('#leftTempleMainCurvatureMin').val($('#popupLeftTempleMainCurvatureMin').val());
                $('#leftTempleMainCurvatureMax').val($('#popupLeftTempleMainCurvatureMax').val());
                $('#leftTempleTerminalAngleStandard').val($('#popupLeftTempleTerminalAngleStandard').val());
                $('#leftTempleTerminalAngleMin').val($('#popupLeftTempleTerminalAngleMin').val());
                $('#leftTempleTerminalAngleMax').val($('#popupLeftTempleTerminalAngleMax').val());
              }
          });
          
          $('#leftTempleDimsPopupButton').click(function() {
            leftTempleDimsConfig.dialog('open');
            return false;
          });
          
          // Left temple decoration popup
          var leftTempleDecorationConfig = $('#leftTempleDecorationConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_left_temple_decoration_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                
                
                // Check any added fields are valid before continuing
                var fieldsValid = validateAllLeftTempleNewTextureVals();
                if ( !fieldsValid ) {
                    alert("Invalid decoration value specified or values missing. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }
                
                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupLeftTempleBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#leftTempleNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupLeftTempleBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#leftTempleRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='leftTempleTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#leftTempleNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='leftTempleTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#leftTempleNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='leftTempleTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#leftTempleNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='leftTempleTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#leftTempleNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='leftTempleTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#leftTempleNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='leftTempleTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#leftTempleNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='leftTempleTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#leftTempleNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupLeftTempleTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#leftTempleTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#leftTempleDecorationPopupButton').click(function() {
            leftTempleDecorationConfig.dialog('open');
            return false;
          });

          // Right temple options popup
          var rightTempleOptions = $('#rightTempleOptionsPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_right_temple_options_popup_title'],
              closeOnEscape: true,
              modal: true
          });
            
          $('#rightTempleOptionsButton').click(function() {
            rightTempleOptions.dialog('open');
            return false;
          });

          // Right temple popup
          var rightTempleConfig = $('#rightTempleConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_right_temple_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                  
                var configValid = validateAllRightTempleConfigVals();
                if ( !configValid ) {
                    alert("Invalid configuration value specified. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }

                // Copy various fields from the popup into hidden fields in the form
                $('#rightTempleFinishingAspect').val($('#popupRightTempleFinishingAspect').val());
                $('#rightTempleMattePrice').val($('#popupRightTempleMattePrice').val());
                $('#rightTempleMatteProductionTime').val($('#popupRightTempleMatteProductionTime').val());
                $('#rightTempleBrilliantPrice').val($('#popupRightTempleBrilliantPrice').val());
                $('#rightTempleBrilliantProductionTime').val($('#popupRightTempleBrilliantProductionTime').val());
              }
          });
          
          $('#rightTemplePopupButton').click(function() {
            rightTempleConfig.dialog('open');
            return false;
          });
          
          // Right temple dimensions popup
          var rightTempleDimsConfig = $('#rightTempleDimsPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_right_temple_dimensions_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 650,
              beforeClose: function(event, ui) { 
                  
                var dataValid = validateAllRightTempleDimensionVals();
                if ( !dataValid ) {
                    alert("Invalid dimension value specified. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }

                // Copy various fields from the popup into hidden fields in the form
                $('#rightTempleLengthStandard').val($('#popupRightTempleLengthStandard').val());
                $('#rightTempleLengthMin').val($('#popupRightTempleLengthMin').val());
                $('#rightTempleLengthMax').val($('#popupRightTempleLengthMax').val());
                $('#rightTempleOpeningAngleStandard').val($('#popupRightTempleOpeningAngleStandard').val());
                $('#rightTempleOpeningAngleMin').val($('#popupRightTempleOpeningAngleMin').val());
                $('#rightTempleOpeningAngleMax').val($('#popupRightTempleOpeningAngleMax').val());
                $('#rightTempleMainCurvatureStandard').val($('#popupRightTempleMainCurvatureStandard').val());
                $('#rightTempleMainCurvatureMin').val($('#popupRightTempleMainCurvatureMin').val());
                $('#rightTempleMainCurvatureMax').val($('#popupRightTempleMainCurvatureMax').val());
                $('#rightTempleTerminalAngleStandard').val($('#popupRightTempleTerminalAngleStandard').val());
                $('#rightTempleTerminalAngleMin').val($('#popupRightTempleTerminalAngleMin').val());
                $('#rightTempleTerminalAngleMax').val($('#popupRightTempleTerminalAngleMax').val());
              }
          });
          
          $('#rightTempleDimsPopupButton').click(function() {
            rightTempleDimsConfig.dialog('open');
            return false;
          });
          
          // Right temple decoration popup
          var rightTempleDecorationConfig = $('#rightTempleDecorationConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_right_temple_decoration_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                  
                // Check any added fields are valid before continuing
                var fieldsValid = validateAllRightTempleNewTextureVals();
                if ( !fieldsValid ) {
                    alert("Invalid decoration value specified or values missing. Please check all values before continuing"); // TODO - make this into a message lookup
                    return false;
                }
  
                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupRightTempleBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#rightTempleNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupRightTempleBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#rightTempleRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='rightTempleTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#rightTempleNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='rightTempleTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#rightTempleNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='rightTempleTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#rightTempleNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='rightTempleTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#rightTempleNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='rightTempleTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#rightTempleNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='rightTempleTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#rightTempleNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='rightTempleTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#rightTempleNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupRightTempleTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#rightTempleTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#rightTempleDecorationPopupButton').click(function() {
            rightTempleDecorationConfig.dialog('open');
            return false;
          });

          // Standards popup
          var standardsConfig = $('#standardsConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_standard_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 400,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#hingeId').val($('#popupStandardsHingeId').val());
                $('#nylonId').val($('#popupStandardsNylonId').val());
                $('#screwId').val($('#popupStandardsScrewId').val());
              }
          });
          
          $('#standardsPopupButton').click(function() {
            standardsConfig.dialog('open');
            return false;
          });

          // Precookeds popup
          var precookedConfig = $('#precookedConfigPopup').dialog({
              autoOpen: false,
              title: localisedMessages['frame_database_precooked_config_popup_title'],
              closeOnEscape: true,
              modal: true,
              width: 800,
              beforeClose: function(event, ui) { 
                  
                  
                // Perform validation to check that the fields are OK and complain if not
                var allValid = validateAllNewPrecookedVals();
                
                if ( !allValid ) {
                  alert("A precooked file configuration has been entered with invalid value(s). Please correct the entry before continuing"); // TODO - change this to a message lookup
                  return false;
                }
  
                // Copy various fields from the popup into hidden fields in the form
                // Combine each of the reference fields separated by _BREAK_
                var refVals = "";
                $("input[name*='popupPrecookedReference']").each(function() {
                  if ( refVals != "" ) {
                    refVals = refVals + "_BREAK_";
                  }
                  refVals = refVals + $(this).val();
                });

                $('#precookedReferenceVals').val(refVals);
                
                // Combine each of the bridge width fields separated by _BREAK_
                var bridgeWidthVals = "";
                $("input[name*='popupPrecookedBridgeWidth']").each(function() {
                  if ( bridgeWidthVals != "" ) {
                    bridgeWidthVals = bridgeWidthVals + "_BREAK_";
                  }
                  bridgeWidthVals = bridgeWidthVals + $(this).val();
                });

                $('#precookedBridgeWidthVals').val(bridgeWidthVals);

                // Combine each of the heel width fields separated by _BREAK_
                var heelWidthVals = "";
                $("input[name*='popupPrecookedHeelWidth']").each(function() {
                  if ( heelWidthVals != "" ) {
                    heelWidthVals = heelWidthVals + "_BREAK_";
                  }
                  heelWidthVals = heelWidthVals + $(this).val();
                });

                $('#precookedHeelWidthVals').val(heelWidthVals);

                // Combine each of the precooked deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupPrecookedDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#precookedDeleteVals').val(deleteVals);
              }
          });
          
          $('#precookedPopupButton').click(function() {
            precookedConfig.dialog('open');
            return false;
          });
          
          $('#addPrecookedButton').click(function() {
            $('#rightTempleLengthStandard').val($('#popupRightTempleLengthStandard').val());
            var numb = parseInt($('#numOfPrecookedRows').val());

            // Create a new row to insert into the table
            var newRowNum = numb + 1;
            
            var newRow = $('<tr></tr>').append('<td></td>')
            var refInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedReference-' + newRowNum + '" name="popupPrecookedReference-' + newRowNum + '"></input>');
            var bridgeWidthInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedBridgeWidth-' + newRowNum + '" name="popupPrecookedBridgeWidth-' + newRowNum + '"></input>');
            var heelWidthInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedHeelWidth-' + newRowNum + '" name="popupPrecookedHeelWidth-' + newRowNum + '"></input>');
            var fileInputCell = $('<td></td>').append('<input type="file" id="popupPrecookedFile-' + newRowNum + '" name="popupPrecookedFile-' + newRowNum + '"></input>');
            var className = "even";
            if ( numb % 2 == 0 )
                className = "odd";
            
              
            newRow.append(refInputCell).append(bridgeWidthInputCell).append(heelWidthInputCell).append(fileInputCell);
            newRow.addClass(className);
            
            
            newRow.insertAfter($('#precookedTable tbody>tr:last'));
            

            $('#numOfPrecookedRows').val(numb+1);

            // Set up validation on blur..
            $('#popupPrecookedReference-'+newRowNum).blur(function() {
              var validationMessage = validateStringPresentVal($(this).val());
              if ( validationMessage )
                  alert(validationMessage);
            });
            $('#popupPrecookedBridgeWidth-'+newRowNum).blur(function() {
               var validationMessage =  validateNumberVal($(this).val());
               if ( validationMessage )
                   alert(validationMessage);
            });
            $('#popupPrecookedHeelWidth-'+newRowNum).blur(function() {
                var validationMessage = validateNumberVal($(this).val());
                if ( validationMessage ) 
                    alert(validationMessage);
            });
            $('#popupPrecookedFile-'+newRowNum).change(function() {
                var validationMessage = validateFileVal($(this).val());
                if ( validationMessage ) 
                    alert(validationMessage);
            });

            return false;
          });
          
          $('#addFrontalColourButton').click(function() {
            
            var newColourSelect = $('#popupFrontalNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#frontalBaseColourList');
              var numOfRows = parseInt($('#numOfFrontalBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupFrontalBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupFrontalBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#frontalBaseColourNoneLinkedEntry').hide();
              $('#frontalBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupFrontalNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfFrontalBaseColourRows').val(numOfRows+1);
              
            } else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });
          
          
          $('#addFrontalTextureButton').click(function() {
            
            var newImageSelect = $('#popupFrontalNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#frontalTextureListTable');
              var numOfRows = parseInt($('#numOfFrontalTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
              
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="frontalTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureScaleFactor-' + numOfRows + '" value="' + formattedFloatZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTexturePrice-' + numOfRows + '" value="' + formattedPriceZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#frontalTextureListNoEntry').hide();
              $('#frontalTextureListTableHeading').show();
              
              
              // Add in validation to the various fields that we've just added
              $('#frontalTextureReference-'+numOfRows).blur(function() {
                 var validationMessage =  validateStringPresentVal($(this).val());
                 if ( validationMessage != "" )
                     alert(validationMessage);
              });
              $('#frontalTextureX-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "")
                      alert(validationMessage);
              });
              $('#frontalTextureY-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              })
              $('#frontalTextureScaleFactor-' + numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#frontalTexturePrice-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#frontalTextureProductionTime-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });

              // Remove the image from the list
              $('#popupFrontalNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfFrontalTextureRows').val(numOfRows+1);

              
            } else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });

          $('#addLeftTempleColourButton').click(function() {
            
            var newColourSelect = $('#popupLeftTempleNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#leftTempleBaseColourList');
              var numOfRows = parseInt($('#numOfLeftTempleBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupLeftTempleBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupLeftTempleBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#leftTempleBaseColourNoneLinkedEntry').hide();
              $('#leftTempleBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupLeftTempleNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfLeftTempleBaseColourRows').val(numOfRows+1);
              
            }else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });
          
          
          $('#addLeftTempleTextureButton').click(function() {
            
            var newImageSelect = $('#popupLeftTempleNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#leftTempleTextureListTable');
              var numOfRows = parseInt($('#numOfLeftTempleTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
                
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="leftTempleTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="numeric" name="leftTempleTextureScaleFactor-' + numOfRows + '" id="leftTempleTextureScaleFactor-' + numOfRows + '" class="required" value="' + formattedFloatZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTexturePrice-' + numOfRows + '" value="' + formattedPriceZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#leftTempleTextureListNoEntry').hide();
              $('#leftTempleTextureListTableHeading').show();
              
              // Add in validation to the various fields that we've just added
              $('#leftTempleTextureReference-'+numOfRows).blur(function() {
                 var validationMessage =  validateStringPresentVal($(this).val());
                 if ( validationMessage != "" )
                     alert(validationMessage);
              });
              $('#leftTempleTextureX-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "")
                      alert(validationMessage);
              });
              $('#leftTempleTextureY-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              })
              $('#leftTempleTextureScaleFactor-' + numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#leftTempleTexturePrice-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#leftTempleTextureProductionTime-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
          
              // Remove the image from the list
              $('#popupLeftTempleNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfLeftTempleTextureRows').val(numOfRows+1);

              
            } else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });

          $('#addRightTempleColourButton').click(function() {
            
            var newColourSelect = $('#popupRightTempleNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#rightTempleBaseColourList');
              var numOfRows = parseInt($('#numOfRightTempleBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupRightTempleBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupRightTempleBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#rightTempleBaseColourNoneLinkedEntry').hide();
              $('#rightTempleBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupRightTempleNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfRightTempleBaseColourRows').val(numOfRows+1);
              
            } else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });
          
          
          $('#addRightTempleTextureButton').click(function() {
            
            var newImageSelect = $('#popupRightTempleNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#rightTempleTextureListTable');
              var numOfRows = parseInt($('#numOfRightTempleTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
                
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="rightTempleTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureScaleFactor-' + numOfRows + '" value="' + formattedFloatZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTexturePrice-' + numOfRows + '" value="' + formattedPriceZero + '"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#rightTempleTextureListNoEntry').hide();
              $('#rightTempleTextureListTableHeading').show();
              
              
              // Add in validation to the various fields that we've just added
              $('#rightTempleTextureReference-'+numOfRows).blur(function() {
                 var validationMessage =  validateStringPresentVal($(this).val());
                 if ( validationMessage != "" )
                     alert(validationMessage);
              });
              $('#rightTempleTextureX-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "")
                      alert(validationMessage);
              });
              $('#rightTempleTextureY-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              })
              $('#rightTempleTextureScaleFactor-' + numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#rightTempleTexturePrice-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#rightTempleTextureProductionTime-'+numOfRows).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
          

              // Remove the image from the list
              $('#popupRightTempleNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfRightTempleTextureRows').val(numOfRows+1);

              
            } else {
              alert(localisedMessages['frame_database_config_popup_no_choice_made_message']);
            }

            return false;
          });


          //************************************************
          // Set up validation for static fields in the interface
          //************************************************
          
          // Frontal
          //************************
          
          // Configuration
          $('#popupFinishingAspect').blur(function() {
              var validationMessage = validateStringPresentVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupMattePrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupMatteProductionTime').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBrilliantPrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBrilliantProductionTime').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                 alert(validationMessage);
          });
          $('#popupMinLensThickness').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTopPupilFrameDistance').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftBottomPupilFrameDistancePSV').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftBottomPupilFrameDistanceMF').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTopPupilFrameDistance').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightBottomPupilFrameDistancePSV').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightBottomPupilFrameDistanceMF').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          
          // Dimensions
          // General dims
          $('#popupBoxingHorizontalStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" ) 
                  alert(validationMessage);
          });
          $('#popupBoxingHorizontalMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBoxingHorizontalMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" ) 
                  alert(validationMessage);
          });
          $('#popupBoxingVerticalStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBoxingVerticalMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBoxingVerticalMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleLeftStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleLeftMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleLeftMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleRightStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleRightMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupPantoscopicAngleRightMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeWidthStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeWidthMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeWidthMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeHeightStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeHeightMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBridgeHeightMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupHeelWidthStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupHeelWidthMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupHeelWidthMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupInternalRimReductionStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" ) 
                  alert(validationMessage);
          });
          $('#popupInternalRimReductionMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupInternalRimReductionMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupBaseOfFrameStandard').blur(function() {
             var validationMessage = validateNumberVal($(this).val());
             if ( validationMessage != "" )
                 alert(validationMessage);
          });
          $('#popupBaseOfFrameMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" ) 
                  alert(validationMessage);
          });
          $('#popupBaseOfFrameMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupWrapAngleStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupWrapAngleMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupWrapAngleMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupTotalLengthStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          
          // Total length dims
          for(var ctr = 1; ctr <= 15; ctr++ ) {
              $('#popupTotalSizeBridgeWidth'+ctr).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#popupTotalSizeMin'+ctr).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              });
              $('#popupTotalSizeMax'+ctr).blur(function() {
                  var validationMessage = validateNumberVal($(this).val());
                  if ( validationMessage != "" )
                      alert(validationMessage);
              })
          }
          
          // Left Temple 
          // ***********************
           
          // Configuration
          $('#popupLeftTempleFinishingAspect').blur(function() {
             var validationMessage = validateStringPresentVal($(this).val());
             if ( validationMessage != "" )
                 alert(validationMessage);
          });
      
          $('#popupLeftTempleMattePrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          
          $('#popupLeftTempleMatteProductionTime').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
      
          $('#popupLeftTempleBrilliantPrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
  
          $('#popupLeftTempleBrilliantProductionTime').blur(function() {
             var validationMessage = validateNumberVal($(this).val());
             if ( validationMessage != "" )
                 alert(validationMessage);
          })
          
          // Dimensions
          $('#popupLeftTempleLengthStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleLengthMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);              
          });
          $('#popupLeftTempleLengthMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleOpeningAngleStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleOpeningAngleMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleOpeningAngleMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleMainCurvatureStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleMainCurvatureMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleMainCurvatureMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleTerminalAngleStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleTerminalAngleMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupLeftTempleTerminalAngleMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          

          // Right Temple 
          // *********************************
          //
          // Configuration
          $('#popupRightTempleFinishingAspect').blur(function() {
             var validationMessage = validateStringPresentVal($(this).val());
             if ( validationMessage != "" )
                 alert(validationMessage);
          });
      
          $('#popupRightTempleMattePrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          
          $('#popupRightTempleMatteProductionTime').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
      
          $('#popupRightTempleBrilliantPrice').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
  
          $('#popupRightTempleBrilliantProductionTime').blur(function() {
             var validationMessage = validateNumberVal($(this).val());
             if ( validationMessage != "" )
                 alert(validationMessage);
          })
          
          // Dimensions
          $('#popupRightTempleLengthStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleLengthMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);              
          });
          $('#popupRightTempleLengthMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleOpeningAngleStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleOpeningAngleMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleOpeningAngleMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleMainCurvatureStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleMainCurvatureMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleMainCurvatureMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleTerminalAngleStandard').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleTerminalAngleMin').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });
          $('#popupRightTempleTerminalAngleMax').blur(function() {
              var validationMessage = validateNumberVal($(this).val());
              if ( validationMessage != "" )
                  alert(validationMessage);
          });


        });

        function submitForm() {
          var formToSubmit = $('#frameForm');
          
          var precookedFileInputs = $('input[name*=popupPrecookedFile]').each(function() {

            formToSubmit.append($(this));
          });

        }
        
      function validateStringPresentVal(val) {
        var retval = "";

        if ( !val || val == "" || val.trim() == "" ) {
          retval = "Bad required string val..";
          // TODO - make this a message lookup...
        }

        return retval;
      }
      
      function validateNumberVal(val) {
          var retval = "";
          
          if ( !val || val != parseFloat(val) ) {
              retval = "Bad number val..";
              // TODO - make into a message..
          }
          
          return retval;
      }
      
      function validateFileVal(val) {
        var retval = "";

        if ( !val || val == "" || val.trim() == "" ) {
          retval = "Bad required file val..";
          // TODO - make this a message lookup...
        }

        return retval;
      }



        function validateAllNewPrecookedVals() {
            
            var allValid = true;
            
            // Go and get the number of rows element so we know what fields we need to consider
            var numOfRows = parseInt($('#numOfPrecookedRows').val());

            // For each of the rows validate each of the entries in turn 
            for ( var ctr = 0; ctr <= numOfRows; ctr++ ) {
              
              var referenceVal = $('#popupPrecookedReference-'+ctr).val();
              var bridgeVal = $('#popupPrecookedBridgeWidth-'+ctr).val();
              var heelVal = $('#popupPrecookedHeelWidth-'+ctr).val();
              var fileVal = $('#popupPrecookedFile-'+ctr).val();
                            
              if ( referenceVal || bridgeVal || heelVal || fileVal ) {
                  // At least one value specified - validate them all..
                  if ( validateStringPresentVal(referenceVal) != "" )
                      allValid = false;
                  if ( validateNumberVal(bridgeVal) != "" )
                      allValid = false;
                  if ( validateNumberVal(heelVal) != "" )
                      allValid = false;
                  if ( validateFileVal(fileVal) != "" )
                      allValid = false;
                  
                  if (!allValid) {
//                      alert("allValid = false. ref: " + referenceVal + " bridge " + bridgeVal + " heel " + heelVal + " file " + fileVal + " ctr = " + ctr);
                      // We've found something that's invalid so stop testing to save time
                      break;
                  }
              } else {
                  // No values specified so just ignore
              }
            }
            
            return allValid;
          }
          
          function validateAllFrontalTempleConfigVals() {
              var allValid = true;
          
              var finishingValid = validateStringPresentVal($('#popupFinishingAspect').val());
              var mattePriceValid = validateNumberVal($('#popupMattePrice').val());
              var matteProdTimeValid = validateNumberVal($('#popupMatteProductionTime').val());
              var brilliantPriceValid = validateNumberVal($('#popupBrilliantPrice').val());
              var brilliantProdTimeValid = validateNumberVal($('#popupBrilliantProductionTime').val());
              var minLensThickValid = validateNumberVal($('#popupMinLensThickness').val());
              var leftTopDistanceValid = validateNumberVal($('#popupLeftTopPupilFrameDistance').val());
              var leftBottomDistancePSVValid = validateNumberVal($('#popupLeftBottomPupilFrameDistancePSV').val());
              var leftBottomDistanceMFValid = validateNumberVal($('#popupLeftBottomPupilFrameDistanceMF').val());
              var rightTopDistanceValid = validateNumberVal($('#popupRightTopPupilFrameDistance').val());
              var rightBottomDistancePSVValid = validateNumberVal($('#popupRightBottomPupilFrameDistancePSV').val());
              var rightBottomDistanceMFValid = validateNumberVal($('#popupRightBottomPupilFrameDistanceMF').val());
              

              if ( finishingValid != "" || mattePriceValid != "" || matteProdTimeValid != "" || brilliantPriceValid != ""
                || brilliantProdTimeValid != "" || minLensThickValid != "" || leftTopDistanceValid != "" 
                || leftBottomDistancePSVValid != "" || leftBottomDistanceMFValid != ""
                || rightTopDistanceValid != "" || rightBottomDistancePSVValid != "" || rightBottomDistanceMFValid != "" ) {
              
                    allValid = false;
              }

              return allValid;
          }
          
          
          
          function validateAllLeftTempleConfigVals() {
              var allValid = true;
              
              var finishingValid = validateStringPresentVal($('#popupLeftTempleFinishingAspect').val());
              var mattePriceValid = validateNumberVal($('#popupLeftTempleMattePrice').val());
              var matteTimeValid = validateNumberVal($('#popupLeftTempleMatteProductionTime').val());
              var brilliantPriceValid = validateNumberVal($('#popupLeftTempleBrilliantPrice').val());
              var brilliantTimeValid = validateNumberVal($('#popupLeftTempleBrilliantProductionTime').val());
              
              if ( finishingValid != "" || mattePriceValid != "" || matteTimeValid != "" || brilliantPriceValid != "" || brilliantTimeValid != "" )  {
                  allValid = false;
              }
              
              return allValid;              
          }
          
          function validateAllRightTempleConfigVals() {
              var allValid = true;
              
              var finishingValid = validateStringPresentVal($('#popupRightTempleFinishingAspect').val());
              var mattePriceValid = validateNumberVal($('#popupRightTempleMattePrice').val());
              var matteTimeValid = validateNumberVal($('#popupRightTempleMatteProductionTime').val());
              var brilliantPriceValid = validateNumberVal($('#popupRightTempleBrilliantPrice').val());
              var brilliantTimeValid = validateNumberVal($('#popupRightTempleBrilliantProductionTime').val());
              
              if ( finishingValid != "" || mattePriceValid != "" || matteTimeValid != "" || brilliantPriceValid != "" || brilliantTimeValid != "" )  {
                  allValid = false;
              }
              
              return allValid;              
          }
          
          function validateAllLeftTempleDimensionVals() {
            var allValid = true;
              
              
            var stdLengthValid = validateNumberVal($('#popupLeftTempleLengthStandard').val());
            var minLengthValid = validateNumberVal($('#popupLeftTempleLengthMin').val());
            var maxLengthValid = validateNumberVal($('#popupLeftTempleLengthMax').val());
            var stdOpenAngleValid = validateNumberVal($('#popupLeftTempleOpeningAngleStandard').val());
            var minOpenAngleValid = validateNumberVal($('#popupLeftTempleOpeningAngleMin').val());
            var maxOpenAngleValid = validateNumberVal($('#popupLeftTempleOpeningAngleMax').val());
            var stdMainCurvatureValid = validateNumberVal($('#popupLeftTempleMainCurvatureStandard').val());
            var minMainCurvatureValid = validateNumberVal($('#popupLeftTempleMainCurvatureMin').val());
            var maxMainCurvatureValid = validateNumberVal($('#popupLeftTempleMainCurvatureMax').val());
            var stdTerminalAngleValid = validateNumberVal($('#popupLeftTempleTerminalAngleStandard').val());
            var minTerminalAngleValid = validateNumberVal($('#popupLeftTempleTerminalAngleMin').val());
            var maxTerminalAngleValid = validateNumberVal($('#popupLeftTempleTerminalAngleMax').val());
            
            if ( stdLengthValid != "" || minLengthValid != "" || maxLengthValid != ""
                || stdOpenAngleValid != "" || minOpenAngleValid != "" || maxOpenAngleValid != ""
                || stdMainCurvatureValid != "" || minMainCurvatureValid != "" || maxMainCurvatureValid != ""
                || stdTerminalAngleValid != "" || minTerminalAngleValid != "" || maxTerminalAngleValid != "" ) {
                allValid = false;
            }
              
            return allValid;              
          }
          
          function validateAllRightTempleDimensionVals() {
            var allValid = true;
              
              
            var stdLengthValid = validateNumberVal($('#popupRightTempleLengthStandard').val());
            var minLengthValid = validateNumberVal($('#popupRightTempleLengthMin').val());
            var maxLengthValid = validateNumberVal($('#popupRightTempleLengthMax').val());
            var stdOpenAngleValid = validateNumberVal($('#popupRightTempleOpeningAngleStandard').val());
            var minOpenAngleValid = validateNumberVal($('#popupRightTempleOpeningAngleMin').val());
            var maxOpenAngleValid = validateNumberVal($('#popupRightTempleOpeningAngleMax').val());
            var stdMainCurvatureValid = validateNumberVal($('#popupRightTempleMainCurvatureStandard').val());
            var minMainCurvatureValid = validateNumberVal($('#popupRightTempleMainCurvatureMin').val());
            var maxMainCurvatureValid = validateNumberVal($('#popupRightTempleMainCurvatureMax').val());
            var stdTerminalAngleValid = validateNumberVal($('#popupRightTempleTerminalAngleStandard').val());
            var minTerminalAngleValid = validateNumberVal($('#popupRightTempleTerminalAngleMin').val());
            var maxTerminalAngleValid = validateNumberVal($('#popupRightTempleTerminalAngleMax').val());
            
            if ( stdLengthValid != "" || minLengthValid != "" || maxLengthValid != ""
                || stdOpenAngleValid != "" || minOpenAngleValid != "" || maxOpenAngleValid != ""
                || stdMainCurvatureValid != "" || minMainCurvatureValid != "" || maxMainCurvatureValid != ""
                || stdTerminalAngleValid != "" || minTerminalAngleValid != "" || maxTerminalAngleValid != "" ) {
                allValid = false;
            }
              
            return allValid;              
          }
          
          function validateAllLeftTempleNewTextureVals() {
              
              
              var allValid = true;
            
              // Go and get the number of rows element so we know what fields we need to consider
              var numOfRows = parseInt($('#numOfLeftTempleTextureRows').val());

              // For each of the rows validate each of the entries in turn 
              for ( var ctr = 0; ctr <= numOfRows; ctr++ ) {

                var referenceVal = $('#leftTempleTextureReference-'+ctr).val();
                var xVal = $('#leftTempleTextureX-'+ctr).val();
                var yVal = $('#leftTempleTextureY-'+ctr).val();
                var scaleVal = $('#leftTempleTextureScaleFactor-'+ctr).val();
                var priceVal = $('#leftTempleTexturePrice-'+ctr).val();
                var prodTimeVal = $('#leftTempleTextureProductionTime-'+ctr).val();
                
                if ( referenceVal || xVal || yVal || scaleVal || priceVal || prodTimeVal ) {
                    // At least one value specifed - validate them all
                    if ( validateStringPresentVal(referenceVal) != "" ) 
                        allValid = false;
                    if ( validateNumberVal(xVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(yVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(scaleVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(priceVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(prodTimeVal) != "" )
                        allValid = false;
                    
                    if (!allValid) {
//                      alert("allValid = false. ref: " + referenceVal + " bridge " + bridgeVal + " heel " + heelVal + " file " + fileVal + " ctr = " + ctr);
                      // We've found something that's invalid so stop testing to save time
                      break;
                    }
                } else {
                      // No values specified so just ignore
                }
              }
              
              return allValid;
          }
          
          function validateAllRightTempleNewTextureVals() {
              
              
              var allValid = true;
            
              // Go and get the number of rows element so we know what fields we need to consider
              var numOfRows = parseInt($('#numOfRightTempleTextureRows').val());

              // For each of the rows validate each of the entries in turn 
              for ( var ctr = 0; ctr <= numOfRows; ctr++ ) {

                var referenceVal = $('#rightTempleTextureReference-'+ctr).val();
                var xVal = $('#rightTempleTextureX-'+ctr).val();
                var yVal = $('#rightTempleTextureY-'+ctr).val();
                var scaleVal = $('#rightTempleTextureScaleFactor-'+ctr).val();
                var priceVal = $('#rightTempleTexturePrice-'+ctr).val();
                var prodTimeVal = $('#rightTempleTextureProductionTime-'+ctr).val();
                
                if ( referenceVal || xVal || yVal || scaleVal || priceVal || prodTimeVal ) {
                    // At least one value specifed - validate them all
                    if ( validateStringPresentVal(referenceVal) != "" ) 
                        allValid = false;
                    if ( validateNumberVal(xVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(yVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(scaleVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(priceVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(prodTimeVal) != "" )
                        allValid = false;
                    
                    if (!allValid) {
//                      alert("allValid = false. ref: " + referenceVal + " bridge " + bridgeVal + " heel " + heelVal + " file " + fileVal + " ctr = " + ctr);
                      // We've found something that's invalid so stop testing to save time
                      break;
                    }
                } else {
                      // No values specified so just ignore
                }
              }
              
              return allValid;
          }


          function validateAllFrontalNewTextureVals() {
              
              
              var allValid = true;
            
              // Go and get the number of rows element so we know what fields we need to consider
              var numOfRows = parseInt($('#numOfFrontalTextureRows').val());

              // For each of the rows validate each of the entries in turn 
              for ( var ctr = 0; ctr <= numOfRows; ctr++ ) {

                var referenceVal = $('#frontalTextureReference-'+ctr).val();
                var xVal = $('#frontalTextureX-'+ctr).val();
                var yVal = $('#frontalTextureY-'+ctr).val();
                var scaleVal = $('#frontalTextureScaleFactor-'+ctr).val();
                var priceVal = $('#frontalTexturePrice-'+ctr).val();
                var prodTimeVal = $('#frontalTextureProductionTime-'+ctr).val();
                
                if ( referenceVal || xVal || yVal || scaleVal || priceVal || prodTimeVal ) {
                    // At least one value specifed - validate them all
                    if ( validateStringPresentVal(referenceVal) != "" ) 
                        allValid = false;
                    if ( validateNumberVal(xVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(yVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(scaleVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(priceVal) != "" )
                        allValid = false;
                    if ( validateNumberVal(prodTimeVal) != "" )
                        allValid = false;
                    
                    if (!allValid) {
//                      alert("allValid = false. ref: " + referenceVal + " bridge " + bridgeVal + " heel " + heelVal + " file " + fileVal + " ctr = " + ctr);
                      // We've found something that's invalid so stop testing to save time
                      break;
                    }
                } else {
                      // No values specified so just ignore
                }
              }
              
              return allValid;
          }

          function validateAllFrontalDimensionVals() {
              var allValid = true;
              
              if ( validateNumberVal($('#popupBoxingHorizontalStandard').val()) != "" )
                allValid = false;
              if ( validateNumberVal($('#popupBoxingHorizontalMin').val()) != "" )
                allValid = false;
              if ( validateNumberVal($('#popupBoxingHorizontalMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBoxingVerticalStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBoxingVerticalMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBoxingVerticalMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleLeftStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleLeftMin').val()) != "" ) 
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleLeftMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleRightStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleRightMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupPantoscopicAngleRightMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeWidthStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeWidthMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeWidthMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeHeightStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeHeightMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBridgeHeightMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupHeelWidthStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupHeelWidthMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupHeelWidthMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupInternalRimReductionStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupInternalRimReductionMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupInternalRimReductionMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBaseOfFrameStandard').val()) != "" ) 
                  allValid = false;
              if ( validateNumberVal($('#popupBaseOfFrameMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupBaseOfFrameMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupWrapAngleStandard').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupWrapAngleMin').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupWrapAngleMax').val()) != "" )
                  allValid = false;
              if ( validateNumberVal($('#popupTotalLengthStandard').val()) != "" )
                  allValid = false;


            return allValid;
          }
          
          
          function validateAllTotalSizeVals() {
              
              var allValid = true;
              
              
              for(var ctr = 1; ctr <= 15; ctr++ ) {
                  if ( validateNumberVal($('#popupTotalSizeBridgeWidth'+ctr).val()) != "" )
                      allValid = false;
                  
                  if ( validateNumberVal($('#popupTotalSizeMin'+ctr).val()) != "" )
                      allValid = false;
                  
                  if ( validateNumberVal($('#popupTotalSizeMax'+ctr).val()) != "" )
                      allValid = false;
                  
                  if ( !allValid )
                      break;
              }
                
                
              return allValid;

          }