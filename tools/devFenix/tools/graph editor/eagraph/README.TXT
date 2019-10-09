                          EA Graphics Editor
                          ------------------

                        Version 0.8.4, 03/32/00

          Copyright (c) 1998-2000 by TASsoft, All Rights Reserved
 Written by Brien Smith (brien@TASsoft.com) & Tim Tschirner (tim@TASsoft.com)


Overview
--------
The EA Graphics Editor is a general purpose graphics viewer and editor,
its intended use is to view and edit Electronic Arts graphic files.
It was primarily written for the NBA Live Series, but it will work
with a lot of files from the NHL Hockey, FIFA Soccer, Need For Speed
and Triple Play Series, too.

The complete EA Graphics Editor package consists of the following files:

README.TXT   -  The file you're just reading
EAGRAPH.EXE  -  EA Graphics Editor, 32-bit Version
                (for use under Windows 95/98 and Windows NT)
EAGRAPH.HLP  -  The Windows Help Files for the EA Graphics Editor
EAGRAPH.CNT

This program is Freeware, so spread it ;-) But, please, leave the
archives contact. Also, NONE of the included files may be changed
in any way. You can always download the latest version from the
EA Graphics Editor Home at the NBA Live Series Center:

       http://www.NBA-Live.com/eagraph


Beta Notes
----------
Please note that this is a Beta release, so it is very likely that there
are bugs and some things will not work as intended. While EAGraph is
capable of viewing a wide variety of Electronic Arts graphic files, there
may be a few file types that are not recognizable yet. Future versions of
EAGraph will likely include the ability to view and edit more file types.
It is also likely that some files of currently known file types are not
displayed correctly and/or don't work at all.

This Beta Version will only work until February 29, 2000. Please keep
checking the EAGraph Home at http://www.NBA-Live.com/eagraph for
updated versions.


Installation
------------
To install, copy EAGRAPH.EXE, EAGRAPH.CNT and EAGRAPH.HLP to any
directory of your choice, that's about it.


Usage
-----
First of all, we'd like to say the following:

!! Use at your own risk. Use of this product may corrupt !!
!! your data files, so please make backups of ALL files. !!

Also, please note that most of our tests have been done with files of
the NBA Live Series.

Please consult the EA Graphics Editor Help file for in-depth instructions
on all things you can do with EAGraph.


Misbehavior, Bugs, Suggestions
------------------------------
Since there is no such thing as a program without bugs, it's likely
that there are some bugs and that some things don't work as they're
supposed. Also, keep in mind that this is a BETA Version.

Please send ALL bug reports and suggestions to eagraph@NBA-Live.com,
but please read the FAQ List in the EAGraph Help File first to see if
your question is already covered.

We will read all reports, but don't expect any answers on them.
Also, before sending ANY FILES, ask us for permission first.


Known Problems
--------------
a) The palette workbench can not currently save transparency information
   back to a fsh file.  I you want to edit transparency do the following.

   1. convert your import file to a 32 bpp bitmap (uses menu Colors |
      Color depth)
   2. open the 32 bpp bitmap
   3. use the transparency editor (button bar) to to edit the bitmap.
      a. push the start to set the whole image transparency to none
      b. click areas of the image and set the transparency and color
         settings you desire.
      c. be sure to push the up-arrow to update the image, and
         the diskette to save the image changes
      d. exit the transparency editor
   4. save the bitmap to file
   5. open and view the target image
   6. import the 32bpp bitmap using the Import Wizard

b) The palette workbench does not work properly for fsh files that
   are TRUE COLOR (15,16,24, & 32 bits per pixel) and are compressed.
   Attempting to edit these files results in a Windows API Kernel32.dll
   error.


Version History
---------------
Version 0.8.4, 03/31/00
             - Fixed a bug in the import wizard that incorrectly 
               reported the free disk space on drives with more 
               than 4 gig of space free.
             - Fixed a bug in the import wizard 'Create new 
               SHPI' option that only recognized 8 bit fsh files.
             - Additional EA file formats supported 
             - Fixed a memory/file read error associted with 
               extracting some *.bin files.
             - Expiration date extended to May 31, 2000!

Version 0.8.3, 02/29/00
             - no new features, just to extend the beta period

Version 0.8.2, 01/30/00
             - corrected the problems in the File Association Dialog

Version 0.8.1, 12/28/99
             - no new features, just to extend the beta period
             - support for Inside Drive 2000 via Plug-In DLL (available
               from the EAGraph Home at http://www.NBA-Live.com/eagraph)
               is now official

Version 0.8.0, 11/30/99
             - added a batch import dialog to the import wizard.
               When importing files, if EAPatch.exe is present in the
               same directory as EAGraph, a button will be available that
               will allow the user to import multiple files into an EA
               style TAR file (e.g. viv, big, etc)
             - added support (read and write) for new Electronic Arts
               file types:
               8-Bit      : 122 (h7A)
               8-Bit (PSX): 64 (h40), 65 (h41) and 193 (hC1 - Read Only)
               RGB Type   : 109 (h6D)
               Palette    : 35  (h23)
             - fixed a bug in the Color Depth dialog that prevented 16
               color palettes from being generated.
             - fixed bug in the import wizard that reported that a file
               was not found when creating a new tar file

Version 0.7.9, 10/29/99
             - the left hand list view is now resizable. Move the mouse to
               the splitter bar between the list view and the picture child
               area. Click and drag the list view to the desired size.
             - improved the Convolve Effect. Effects are now added to the
               whole picture without a 'white frame' around the newly
               'convolved' graphic.
             
Version 0.7.8, 09/30/99
             - fixed the Bug where exiting the zoom view using the
               zoom-resize button would draw a line from the upper
               left corner (0,0) of the unzoomed picture to the point
               pressed on the zoom-resize button (approx 300, 300)
             - fixed the Bug where it was impossible to exit the zoom
               view by right clicking the zoom tool when using the zoom
               tool on the zoomview. Every time the user would exit, the
               click even would trigger a zoom again.
             - fixed a bug where a selected area of a picture was lost the
               next time the image recieved focus
             - the user toolbar configuration is now saved
             - greatly improved the Colorizer tool

Version 0.7.7, 08/31/99
             - added a Zoom Tool and Zoom View
               Only a limited set of editing features are available
               under the Zoom View:
               a. The zoom view is activated by pressing the zoom
                  tool (magnifying glass) and clicking on an image.
                  The picture will be zoomed to 8X magnification.
               b. Editing tools available during zoom editing are:
                  Pen, ellipse, rectangle, line, and fill
               c. User selections are also allowed but only for
                  rectangular selections. As soon as the mouse is
                  released the selection region is copied to the
                  clipboard. The user may then reposition the
                  selected area as needed. Click outside of the
                  selection rectangle to paste permanantly.  Press
                  Undo to discard paste.
               d. The small button in the lower right of the zoom
                  window allows zoom in (left click mouse) or zoom out
                  (right click mouse). The same may be accomplished
                  by using the zoom tool during zoom mode.
             - added the ability to read the ICO graphic file format
             - added a view menu item so that the various tool bars could
               be added or removed to the user's liking
             - added background effects under the Effects menu.
               Included are gradient and plasma effects.

Version 0.7.6, 07/31/99
             - removed the automatic tips in the TreeView
             - fixed an import problem that may have lead to incorrect
               palette information and a garbled import appearance

Version 0.7.5, 06/28/99
             - added a graphic search utility
               (Usage: Right click on the listview and a search dialog
                will appear. You may search for any combination of letters
                that will appear in the graphic name. If an item is found,
                it will be aligned in the top of the list view. If there
                are too few items to fill the list view after the found
                item, the found graphic will appear somewhere in the current
                listing)
             - fixed palette problems that caused some imported graphics
               to have garbled jersey numbers or green tinted graphics

Version 0.7.4, 05/31/99
             - nothing new, just to extend the beta period

Version 0.7.3, 05/17/99
             - Added support for new palette type (44) that is used in
               the Tiger Woods PGA series
             - Added support for saving 8-bit pak style fsh files. These
               files have only the pixel section compressed
             - Repaired a bug where using the palette workbench for true
               color fsh files caused a memory access error or
               'kernel32.dll' error. There still seem to be some memory
               access problems here
             - Fixed bug where the global palette (!pal) was ignored and
               pictures appeared garbled (i.e., random palette). The NHL99
               Faces99.viv still shows the second face incorrectly. This
               is NOT presently believed to be a bug with the way the EAGE
               reads the picture
             - Fixed bug where some pictures did not appear in the game
               after using the Import Wizard. Jersey numbers were
               reported as missing in the game.
             - Added offset support under the FSH save dialog. The X
               and Y values allow the user to offset the image to the
               right (X) and down (Y) by entering values greater than
               zero

Version 0.7.2, 04/29/99
             - added the ability to read RLE compressed TGA files in 8,
               16, 24 and 32 bpp. This compatability was added primarily
               to support the file preview function in the Import Wizard,
               which would previously crash if the user attempted to view
               a compressed TGA file
             - added a transparency editor for non-SHPI (i.e., bmp, png,
               tga, etc.) files. The editor is accessed from the button
               bar
             - corrected a small bug that caused some 8 bpp file imports
               to have palette errors (garbled or green tinted appearance).
               This only appeared when the target image was 8 bpp, and only
               affects some EA FSH types.
               Special Thanks to Beta Testers who help ID this problem:
               Gavin Dellbridge
               Jorge Ortega
             - changed the menu item "Colors | Color Reduction" to
               "Color Depth". Now all images (not just fsh files) can
               be converted to 4, 8, 16, 24 and 32 bit per pixel depth.
               All edit tools apply to these depths but Effects are still
               specific to 8 and 24 bpp.
             - added specifier in the statusbar to further designate true
               color FSH types. After the RGB designation will appear one
               of four numbers: 120 = 16 bit (RRRRRGGGGGXBBBBB);
               125 = 32 bit; 126 = 16 bit (XRRRRRGGGGBBBBB); 127 = 24 bit.
             - added 'Auto-Convert PNG to 32 Bpp' option to the options
               dialog. When checked, this option will convert a 24 bpp image
               with Alpha channel to a 32 bit image. This 32 bit image is
               now suitable for converting to a true color FSH file with
               full transparency import.
             - added the ability to import a 24 bit per pixel (with
               Alpha Channel) PNG file into a 16 or 24 bit FSH file
               (also with transparency)
             - added the ability to save a PNG file with alpha channel
               to a 16 or 24 bit fsh file (also with transparency)
               NOTE: converting a 24 bit alpha PNG to a 16 bit FSH MAY
               yield undesirable results IF the PNG uses opacity rather
               than strict transparency.
             - added the ability to import a 24 bit per pixel (with Alpha
               Channel) TGA file into a 16 or 24 bit fsh file (also with
               transparency)
             - added the ability to import a TGA file with alpha channel
               to a 16 or 24 bit fsh file (also with transparency)
               NOTE: converting a 24 bit alpha PNG to a 16 bit FSH MAY
               yield undesirable results IF the PNG uses opacity rather
               than strict transparency.
             - added a transparency group box to the fsh save dialog:
               'None' sets all alpha values to 255 in true color images,
               'Use Source' uses the alpha values present in the source
               file (recommended for PNG imports)
             - added READ ONLY file detection for the Import Wizard and
               the  Palette Workbench. Previously imports on READ ONLY
               attributed files gave no error message.
             - added READ ONLY removal option
             - Extended the Palette Workbench to include True Color FSH
               files. For true color files, the palette workbench can
               edit an image's opacity for both non-compressed and
               compressed SHPI files

Version 0.7.1, 04/12/99
             - fixed a bug in the ImportWizard where some file types
               were not correctly imported and showed up with garbled
               blue and purple colors
             - fixed a problem where a wrong icon was displayed for
               EAGraph

Version 0.7.0, 04/05/99
             - added support for more EA File Types
             - better support for already supported EA File Types
             - incorporated many new file formats into the Import Wizard:
               bmp, fsh, gif, jpg, pcx, png and tga
               The Import file may be of ANY format (8 to 32 bits per
               pixel) and the Import Wizard now recognizes and allows
               import to nearly any FSH format (8 to 32 Bits per pixel;
               a number of palette types)
               EAGraph 'registers' these formats so that they are viewable
               from the file browser ('add file to list') in ImpWiz. This
               means that you get to preview all files as you browse for
               them
             - added support for the TGA file format (read only) so that
               you can view game shots saved using the F12 key
               (the current TGA object only reads non-compressed TGA images,
               but EA does not use compressed TGA in any of their games)
             - added read-only support for Adobe TIFF files
               (non-compressed)
             - added support for the file formats Windows MetaFile and
               Windows Extended MetaFile
               EAGraph reads and writes WMF/EMF except that writing this
               format does not yield a true vector graphic but a bitamp in
               a metafile shell (not recommended though)
             - fixed bug where opening and then closing a picture child
               using the 'x' button on the child prevented the picture from
               being opened again from the Most Recently Used List
             - fixed Most Recently Used list problem where the list was only
               accessible when no other files were open
             - added a SHPI object to reduce memory leaks and program
               instability for most file types
             - Import Wizard will now transfer transparency chunk information
               from PNG files. This means that FSH files can be created with
               opacity settings from an external editor. Any PNG file that
               contains a tRNS chunk will work.
             - fixed a bug that sometimes included a 20 color palette in
               24 bit images 'Saved as BMP'. Although EAGraph would
               successfully read these images other programs could not.
             - fixed a bug where the Picture Child canvas could not be
               unlocked and therefore wasted system resources while EAGraph
               was running
             - added a number of special effects including a convolve tool,
               flip horizontal, flip vertical, rotate to any angle, contrast,
               brightness, and embossing
             - added a number of Colors features including count colors used,
               a color replacer, palette save and import functions, color
               reduction, Grayscale, and negative colorizer
             - added a number of paint tools including draw, line, circle,
               rectangle, etc.
             - added a text tool to add text to images
             - added a selection tool to select rectangular regions for cut,
               copy, paste, paste as new, paste as selection, etc.
             - added a wand tool to select odd shaped graphics components for
               cut, copy, paste, etc.
             - added a free select tool to cut, copy, and paste odd shapes
             - added the ability to resize and resample the current picture
               child canvas
             - added 16,24,and 32 bit per pixel options in the 'Save As FSH'
               function
             - added a dialog to save the palette in multiple formats in the
               Palette Workbench
             - added a number of tips to the main status bar which are
               visible when the mouse cursor passes over buttons or other
               objects
             - added an Option Dialog where several preferences can be set
             - added support for Triple Play 2000 graphic Files
             - added a Windows Help File
             - added a File Associations Dialog to allow various graphic
               file extensions to be associated with EAGraph
             - lots of small bug fixes and cosmetic changes

Version 0.6.2, 03/30/99
             - nothing new, just to extend the beta period
               (progress on V0.7.0 is coming along very well and
                it will be released pretty soon)

Version 0.6.1, 01/30/99
             - added ability to save in PCX Format
             - increased building speed of TAR Archives

Version 0.6.0, 12/10/98
             - completely rewrote the Import Guardian. It is now
               called Import Wizard and always available (not just with
               open files) and will work correctly with the files who
               it had problems with before
             - added the option to save as an uncompressed file to
               the 'Extract File' Option
             - added File Type indicator to main window caption.
               There are eleven possibilities :
               <SHPI>; <BIGF>; <COFB>; <LZSS/SHPI>; <HUFF/SHPI>; <PAK>;
               <Bitmap>; <JPEG>; <Gif>; <PNG>; <PCX>
             - fixed Bug where filename remained in caption bar after file
               was closed
             - added 'checkerboard' background for better detection
               of transparency to the Palette Workbench Preview function

Version 0.5.7, 11/29/98
             - added JPEG dialog to allow the user to control
               compression factor
             - added ability to save to PNG
             - added PNG dialog with several PNG save features

Version 0.5.6, 10/28/98
             - fixed the problems with the Import Guardian where
               files were not imported correctly though the Import
               Guardian claimed all was successful
               (this was a problem involving different file handling
                under NT and Windows 95/98)
             - fixed a problem in the Import Guardian where the wrong
               file was handled when the user cancelled or did go
               back in the dialog

Version 0.5.5, 09/30/98
             - extended the beta period to November 1998.
             - Compression Manager Removed!
             - LZSS Compression added as an option when
               saving individual files
             - Import Guardian now automatically handles
               compression. It also compresses to COFB files
             - Added PNG read support (still can not save to 
               PNG) NOTE: PNG read does not currently support 
               transparency but will in the near future
             - Added PCX read (16 and 256 color only) support.  
               Not likely to add PCX write support due to 
               declining popularity of PCX format.
             - New Program Icon compliments of Angelo Mauceri 

Version 0.5.4, 08/31/98
             - nothing new, just to extend the beta period
               (we're both still swamped with work but are
                making good progress on V0.6.0)

Version 0.5.3, 08/05/98
             - added user notification when trying to import a
               file into a C0FB File
               (this is the reason for the infamous "I/O Error 87"
                We're already working on support for this operation,
                it'll hopefully make it to the next release)

Version 0.5.2, 07/28/98
             - nothing new, just to extend the beta period
               (we're both swamped with work but will get rid
                of the known bugs as soon as possible)

Version 0.5.1, 06/27/98
             - greatly improved Compression Algorithm

Version 0.5.0, 06/13/98
             - added ability to extract C0FB subfiles
             - added palette identifier to status bar
             - added the ability to save palettes in EA FSH format from
               the Palette WorkBench
             - added support for palette type (P45) _RGB16
             - improved handling for palette type RG_B16
             - added better FSH Save Dialog Box with enhanced features
             - fixed small bug in 'Save to FSH' function that causes
               the FSH header to have incorrect file size data
             - added feature to compression manager that allows to
               build Multi-Pic Shape file
             - fixed a bug in the Compression Manager that corrupted
               files that were larger than 65536 bytes before
               compression (Compression Manager now accepts files
               up to 16 Meg)
             - fixed bug in the compression algorithm that occasionally
               flagged a premature end of file
             - the ListView now never losses focus when a picture child
               is loaded
             - added ability to navigate the ListView with arrow keys
             - added icons to the ListView to graphically designate
               the type of file loaded
             - fixed a bug that prevented viewing palettes for some LZ
               compressed files
             - added support (View only) for 32 Bpp fsh files

Version 0.4.4, 05/24/98
             - added Hex View Option
             - added four-letter descriptor when saving to FSH
               file
             - added support for *.ALL Files (read only)
             - added support for BGRA and older RGB (3 byte)
               palettes
             - fixed a bug that occasionally locked up the
               app upon closing files
             - fixed a bug when the *.FSH Extension was always
               added when extracting a file
             - sped up the TreeView rebuild when closing a file

Version 0.4.3, 05/18/98
             - added Drag'N'Drop Support
             - fixed Small color select bug in the Palette Workbench
             - fixed Compression Manager bug where files that were
               added to a TAR were unrecognized in the game
             - added support for C0FB TAR Files (read only)

Version 0.4.2, 05/10/98
             - fixed a bug in the Compression Manager where
               it would create duplicate files
             - fixed a bug in the Compression Manager where
               it would report a drive as being empty

Version 0.4.1, 05/07/98
             - fixed the opacity bug in the Import Guardian
             - added a Close File Menu Entry and Button
             - improved File Handling under Windows NT
             - added EAGRAPH.INI File which holds Window Size, Position
               and State
             - added Most Recently Used Files List

Version 0.3.0, 05/05/98
             - fixed the opacity bug in the Palette Workbench
             - fixed a bug with saving fsh files which didn't work
               right on Windows 95 systems and cause black images
               (it seems Windows 95 and Windows NT calculate some
                things differently)

Version 0.2.0, 05/02/98
             - fixed a bug in the save to FSH problem where the FSH
               header was saved with false information and caused
               a "Read Beyond End Of File" Error
             - added an undo button on the Palette Workbench form
             - fixed a bug where File Extensions were not automatically
               added when saving

Version 0.1.0, 04/30/98
             - first public beta


Acknowledgements
----------------
- Toni Wilen and JoyPad for their useful information
- The people at EAC (you know who you are)
- FB for giving us something to fill our free time with
- our families for always being there
- All our supporters, we couldn't have done this without you


Legal Information
-----------------
Please note that neither the authors nor TASsoft are affiliated with
Electronic Arts in ANY way.
By using EA Graphics Editor (EAGraph), you accept the following
statements:

1. Use of EAGraph may violate licensing agreements between you and
   Electronic Arts
2. Regarding the use of EAGraph, the following acts MAY be deemed illegal
   in a court of law:
   a. The distribution of any graphic supplied with an Electronic Arts
      product to any 3rd Party.
   b. The display of any graphic as supplied by Electronic Arts in any
      other form than was intended, including Internet Web Pages or other
      electronic media.

NO WARRANTIES. TASsoft expressly disclaims any warranty for this SOFTWARE
PRODUCT. The SOFTWARE PRODUCT and any related documentation is provided
"as is" without warranty of any kind, either expressed or implied,
including, without limitation, the implied warranties or merchantability,
fitness for a particular purpose, or noninfringement. The entire risk
arising out of use or performance of the SOFTWARE PRODUCT remains with
the user.

NBA Live, NHL Hockey, FIFA Soccer, Need For Speed, Triple Play, Electronic
Arts and EA Sports are registered trademarks of Electronic Arts, Inc.

Windows 95, Windows 98 and Windows NT are registered trademarks of
Microsoft Corporation

Delphi and Borland are registered trademarks of Inprise Corporation

GIF is a registered trademark of Compuserve, Inc.

JPEG is a registered trademark of the Joint Photographic Experts Group

All other not specifically mentioned trademarks are registered
trademarks of their respective owners and hereby acknowledged.
