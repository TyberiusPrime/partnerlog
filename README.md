# partnerlog

Minimal Android app (v0) with one screen containing a title and an Exit button.

CI workflows:
- PR workflow builds and uploads a debug APK.
- Release workflow builds and uploads a debug APK and attaches it to the GitHub release.

APK signing uses a repository-stored debug keystore (`/signing/debug.keystore`) so builds stay upgrade-compatible.
This key is for debug APKs only and must not be reused for production signing. CI should set `PARTNERLOG_DEBUG_KEYSTORE_PASSWORD` and `PARTNERLOG_DEBUG_KEY_PASSWORD` secrets (mapped to Gradle properties in workflows).
