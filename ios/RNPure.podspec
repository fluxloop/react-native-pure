
Pod::Spec.new do |s|
  s.name         = "RNPure"
  s.version      = "1.0.0"
  s.summary      = "RNPure"
  s.description  = <<-DESC
                  RNPure
                   DESC
  s.homepage     = "https://fluxloop.com/"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source_files  = "RNPure/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "PureSDK"
end

  
