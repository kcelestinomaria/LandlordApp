
---

# Lab 4 Report: ViewModel, LiveData, MutableLiveData, and Data Binding Integration

**Project:** LandlordApp
**Author:** Celestine Maria ([@kcelestinomaria](https://github.com/kcelestinomaria))
**Date:** 08/08/2025

---

## Overview

In this lab, we refactored the `AddTenantActivity` workflow in the LandlordApp to follow **modern Android app architecture**. We replaced manual UI updates with reactive components and separation of concerns using:

* `ViewModel`
* `LiveData` & `MutableLiveData`
* `Data Binding`
* `Transformations.map()` for dynamic UI transformation
* BONUS: Reactive tenant counter using LiveData

This aligns the app with the **MVVM (Model–View–ViewModel)** pattern for maintainable, testable code.

---

## What We Did

### 1. Setup

* Cloned the base repo
* Enabled `dataBinding` in `build.gradle`
* Added `lifecycle-livedata` and `lifecycle-viewmodel` dependencies

### 2. Created `TenantViewModel`

Used LiveData to hold and transform tenant info:

* Stores the list of tenants in `MutableLiveData<String>`
* Uses `LiveData<String>` for safe observation
* `Transformations.map()` capitalizes all display text
* Tracks the number of tenants with `LiveData<Int>`

```kotlin
val capitalizedTenantInfo: LiveData<String> = Transformations.map(tenantInfo) {
    it.uppercase()
}
```

### 3. Updated XML Layout

In `addtenant_activity.xml`:

* Wrapped everything in `<layout>`
* Declared a ViewModel variable in `<data>`
* Replaced `TextView` binding with:

```xml
android:text='@{viewModel.capitalizedTenantInfo}'
```

* Added:

```xml
android:text='@{"Tenant Count: " + viewModel.tenantCount}'
```

### 4. Refactored Activity

In `AddTenant.kt`:

* Used `DataBindingUtil` and `by viewModels()`
* Set `binding.lifecycleOwner` to auto-update on LiveData changes
* On click, extracted form values, passed to ViewModel, and cleared fields

---

## What We Learned

| Concept                 | Description                                                          |
| ----------------------- | -------------------------------------------------------------------- |
| `ViewModel`             | Keeps state during configuration changes                             |
| `LiveData`              | UI reacts automatically to state changes                             |
| `Data Binding`          | Declarative connection between UI and logic                          |
| `Transformations.map()` | Lets us transform data on the fly in a clean way                     |
| MVVM                    | Clean architecture that separates concerns and increases testability |

---

## BONUS: Tenant Counter

We added a **reactive counter** to show how many tenants were added using:

```kotlin
private val _tenantCount = MutableLiveData<Int>(0)
val tenantCount: LiveData<Int> = _tenantCount
```

Each time `addTenant()` is triggered, the count is incremented. The UI updates automatically using:

```xml
android:text='@{"Tenant Count: " + viewModel.tenantCount}'
```

No extra logic in the Activity or manual UI refresh is needed — it's purely LiveData-driven.

---

## How to Install & Run

### Requirements

* Android Studio (Arctic Fox or newer)
* Android Emulator or Android phone
* Android SDK 29+

### Steps

```bash
git clone https://github.com/kcelestinomaria/LandlordApp.git
cd LandlordApp
```

1. Open the folder in **Android Studio**
2. Let **Gradle Sync** complete
3. Click **Run** (select your emulator or device)
4. Test the **Add Tenant** functionality

### Expected Behavior

* Enter tenant name, unit, and rent → Tap Add
* See details in UPPERCASE
* See live tenant count
* Rotate screen → data remains

---

## GitHub Repository

> [https://github.com/kcelestinomaria/LandlordApp](https://github.com/kcelestinomaria/LandlordApp)

---
