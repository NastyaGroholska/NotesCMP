package com.ahrokholska.create_note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerImageURL
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject

@Composable
actual fun rememberGalleryManager(onResult: (String) -> Unit): GalleryManager =
    remember {
        GalleryManager {
            val imagePicker = UIImagePickerController().apply {
                setSourceType(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary)
                setAllowsEditing(false)
                setDelegate(object : NSObject(), UIImagePickerControllerDelegateProtocol,
                    UINavigationControllerDelegateProtocol {
                    override fun imagePickerController(
                        picker: UIImagePickerController, didFinishPickingMediaWithInfo: Map<Any?, *>
                    ) {
                        val imageURL =
                            didFinishPickingMediaWithInfo[UIImagePickerControllerImageURL] as? NSURL
                        imageURL?.let {
                            onResult(imageURL.toString())
                        }
                        picker.dismissViewControllerAnimated(true, null)
                    }
                })
            }
            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                viewControllerToPresent = imagePicker, animated = true, completion = null
            )
        }
    }
