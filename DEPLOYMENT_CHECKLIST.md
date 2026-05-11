# Deployment Checklist - Siri-Dhanya Hub

## Pre-Deployment (Development Phase)

### Testing

- [ ] All features tested on multiple devices (sizes 5", 7", 10")
- [ ] Portrait and landscape orientations verified
- [ ] Tested on Android 8.0, 10, 12, 14+
- [ ] All UI screens verified for earthy design consistency
- [ ] RecyclerView/LazyColumn performance verified
- [ ] Image loading works with Glide
- [ ] Search functionality returns correct results
- [ ] Save/unsave recipes works smoothly
- [ ] Price trends calculate correctly
- [ ] Navigation between tabs smooth

### Code Quality

- [ ] No lint errors or warnings
- [ ] Proguard rules configured
- [ ] BuildConfig optimized
- [ ] Remove debug logs
- [ ] All TODOs resolved
- [ ] No hardcoded strings (use strings.xml)
- [ ] Proper error handling implemented

### Firebase Setup

- [ ] Firebase project created
- [ ] Realtime Database configured
- [ ] Test mode rules converted to production rules
- [ ] Sample data loaded
- [ ] Backups enabled
- [ ] Monitoring enabled

### API Integration

- [ ] Google Generative AI API key generated
- [ ] API key securely stored
- [ ] API quota sufficient
- [ ] Error handling for API failures

## Build Preparation

### Code Changes

- [ ] Update version code in build.gradle.kts
- [ ] Update version name (1.0.0)
- [ ] Update minSdkVersion if needed
- [ ] Update targetSdkVersion to latest
- [ ] All dependencies up to date

### Release Build

- [ ] Generate release keystore
  ```bash
  keytool -genkey -v -keystore ~/siri-dhanya-hub.keystore \
    -keyalg RSA -keysize 2048 -validity 10000 \
    -alias siri-dhanya-release
  ```
- [ ] Build signed APK/Bundle
- [ ] APK size < 50 MB
- [ ] Test signed APK on device
- [ ] Verify app functionality post-build

### Documentation

- [ ] README.md complete and tested
- [ ] QUICK_START.md reviewed
- [ ] API documentation ready
- [ ] Architecture diagrams created
- [ ] User guide created

## Google Play Store Submission

### Prepare Store Listing

- [ ] App icon (512×512px, PNG)
- [ ] Feature graphic (1024×500px)
- [ ] Screenshots (minimum 4, maximum 8)
  - [ ] Mandi Watch screen
  - [ ] Recipe Lab with search
  - [ ] Health Benefits
  - [ ] Direct Buy
  - [ ] Additional differentiating features
- [ ] Short description (80 characters max)
  ```
  "Live millet prices, recipes & nutrition from local farmers"
  ```
- [ ] Full description (4000 characters)
- [ ] Release notes for v1.0.0

### Content Rating

- [ ] Complete content questionnaire
- [ ] Get IARC rating certificate
- [ ] Review and save content rating

### Privacy & Permissions

- [ ] Create Privacy Policy
- [ ] Create Terms of Service
- [ ] Review requested permissions
  - [ ] INTERNET
  - [ ] ACCESS_NETWORK_STATE
- [ ] Explain each permission

### Target Audience

- [ ] Set content rating (Everyone / 12+ / 16+ / 18+)
- [ ] Set primary category: Lifestyle
- [ ] Set secondary category: Food & Drink
- [ ] Choose target regions/countries

### Pricing & Distribution

- [ ] Set to FREE
- [ ] Select countries to distribute:
  - [ ] India (priority)
  - [ ] All other regions (optional)
- [ ] Configure country-specific guidelines

## Deployment Day

### Final Checks

- [ ] All team members notified
- [ ] Support email/contact ready
- [ ] Customer support templates prepared
- [ ] Monitoring dashboards set up

### Upload to Play Store

- [ ] Create app in Google Play Console
- [ ] Upload signed bundle (AAB format preferred)
- [ ] Upload APK as fallback
- [ ] Verify upload successful
- [ ] Check for any warnings/errors

### Submit for Review

- [ ] Review all store listing details
- [ ] Accept Play Store Policies
- [ ] Click "Submit for Review"
- [ ] Note review submission time

### Post-Submission

- [ ] Monitor review status (2-3 hours typically)
- [ ] Keep phone ready for any responses
- [ ] Prepare issue response if needed
- [ ] Monitor first 24 hours closely

## Post-Launch (After Approval)

### Day 1 Activities

- [ ] Announce on social media
- [ ] Send email to stakeholders
- [ ] Monitor crash reports
- [ ] Check user ratings
- [ ] Monitor download statistics
- [ ] Respond to user reviews
- [ ] Set up customer feedback channel

### Week 1 Monitoring

- [ ] Check daily active users (DAU)
- [ ] Monitor retention rate
- [ ] Track crash-free users
- [ ] Analyze feature usage
- [ ] Review user reviews/ratings
- [ ] Monitor Firebase analytics

### Issue Resolution

- [ ] Priority 1 (Critical): Fix within 4 hours
- [ ] Priority 2 (High): Fix within 24 hours
- [ ] Priority 3 (Medium): Fix within 1 week
- [ ] Priority 4 (Low): Fix in next update

### Update Planning

- [ ] Plan updates for issues found
- [ ] Gather feature requests
- [ ] Create roadmap for v1.1
- [ ] Document learnings

## Performance Metrics to Track

### User Metrics

- [ ] Downloads (target: 1000+ in first month)
- [ ] Daily Active Users (DAU)
- [ ] Monthly Active Users (MAU)
- [ ] User retention rate
- [ ] Uninstall rate

### Technical Metrics

- [ ] Crash-free users %
- [ ] Average session length
- [ ] Feature usage statistics
- [ ] API response times
- [ ] Firebase database reads/writes

### Business Metrics

- [ ] App rating (target: 4.5+)
- [ ] Review sentiment analysis
- [ ] User feedback themes
- [ ] Feature adoption rates

## Rollback Plan

If critical issues found:

1. [ ] Create hotfix branch
2. [ ] Fix issue
3. [ ] Test thoroughly
4. [ ] Upload new bundle
5. [ ] Request expedited review
6. [ ] Prepare apology message
7. [ ] Communicate timeline to users

## Success Criteria

✅ **Launch Success**

- Deployment completed without critical errors
- App appears on Google Play Store
- Users can download and install
- All features functional
- Rating 4.0+

✅ **Post-Launch Metrics (First Month)**

- 1000+ downloads achieved
- Crash-free users: >95%
- Daily active users growing
- Positive user reviews

---

**Deploy with Confidence! 🚀**

Last updated: May 11, 2026
