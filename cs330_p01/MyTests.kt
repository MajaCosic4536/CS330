package com.example.cs330_p01

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs330_p01.common.ChoseWeatherIcon
import com.example.cs330_p01.common.DetailImage
import com.example.cs330_p01.common.NavigationBar
import com.example.cs330_p01.common.ShoesList
import com.example.cs330_p01.database.DBCategory
import com.example.cs330_p01.database.DBClothes
import com.example.cs330_p01.database.getMyColorList
import com.example.cs330_p01.database.getSortingCategory
import com.example.cs330_p01.navigation.NavSetup
import com.example.cs330_p01.screens.SplashScreen
import com.example.cs330_p01.screens.SplashScreenView
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testRequestForInternet() {
        rule.setContent {
            val granted = remember {
                mutableStateOf(false)
            }
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                granted.value = isGranted
            }
            SplashScreenView(launcher = launcher)
        }
        rule.onNodeWithText("Hello!").assertExists()
        rule.onNodeWithText("Hello!").performClick()
        rule.onNode(
            hasText("Hello!") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testRequestForCamera() {
        rule.setContent {
            val granted = remember {
                mutableStateOf(false)
            }
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                granted.value = isGranted
            }
            SplashScreenView(launcher = launcher)
        }
        rule.onNode(
            hasContentDescription("Camera button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Camera Icon") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testRequestForGallery() {
        rule.setContent {
            val granted = remember {
                mutableStateOf(false)
            }
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                granted.value = isGranted
            }
            SplashScreenView(launcher = launcher)
        }
        rule.onNode(
            hasContentDescription("Image button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Image Icon") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testHomeBtn() {
        rule.setContent {
            NavigationBar(
                homeScreen = { /*TODO*/ },
                pickerScreen = { /*TODO*/ },
                addScreen = { /*TODO*/ },
                closetScreen = {})
        }
        rule.onNode(
            hasContentDescription("Home button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Home button") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testPickerBtn() {
        rule.setContent {
            NavigationBar(
                homeScreen = { /*TODO*/ },
                pickerScreen = { /*TODO*/ },
                addScreen = { /*TODO*/ },
                closetScreen = {})
        }
        rule.onNode(
            hasContentDescription("Picker button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Picker button") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testAddBtn() {
        rule.setContent {
            NavigationBar(
                homeScreen = { /*TODO*/ },
                pickerScreen = { /*TODO*/ },
                addScreen = { /*TODO*/ },
                closetScreen = {})
        }
        rule.onNode(
            hasContentDescription("Add button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Add button") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testClosetBtn() {
        rule.setContent {
            NavigationBar(
                homeScreen = { /*TODO*/ },
                pickerScreen = { /*TODO*/ },
                addScreen = { /*TODO*/ },
                closetScreen = {})
        }
        rule.onNode(
            hasContentDescription("Closet button") and hasClickAction()
        ).performClick()
        rule.onNode(
            hasContentDescription("Closet button") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testImageDetails() = runTest {
        TestCase.assertNotNull(DetailImage.getItem())
    }

    @Test
    fun testChoseWeatherIcon() = runTest {
        TestCase.assertEquals(ChoseWeatherIcon(0),R.drawable.sunacak)
    }

    @Test
    fun testGetMyColorList() = runTest {
        TestCase.assertNotNull(getMyColorList())
    }

    @Test
    fun testGetSortingCategory() = runTest {
        TestCase.assertNotNull(getSortingCategory())
    }

}